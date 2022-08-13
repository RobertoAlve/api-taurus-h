package com.taurusmagister.taurusmagister.entidade;

import com.taurusmagister.taurusmagister.repositorio.CargoRepository;
import com.taurusmagister.taurusmagister.repositorio.PublicacaoRepository;
import com.taurusmagister.taurusmagister.repositorio.UsuarioBasicoRepository;

import java.nio.file.Files;
import java.nio.file.Path;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ManipularCsv {

    public void gravaArquivoCsv(ListaObj<UsuarioBasico> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);

            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                UsuarioBasico usrBas = lista.getElemento(i);

                saida.format("%s;%s\n", usrBas.getNome(), usrBas.getEmail());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public void leArquivoCsv(byte[] nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        String FILEPATH = "arquivo.txt";
        File file = new File(FILEPATH);

        try {
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            os.write(nomeArq);
            os.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        try {
            arq = new FileReader(file);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%-15s %-15s\n", "Nome", "Email");
            while (entrada.hasNext()) {

                String nome = entrada.next();
                String email = entrada.next();

                System.out.printf("%-15s %-15s\n", nome, email);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void gravaRegistro(String registro, File file) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo: " + erro);
        }

    }

    public StringBuilder exportaArquivoTxt(ListaObj<UsuarioBasico> listaUserBasico, ListaObj<Publicacao> listaPublicacao, String nomeArq) throws IOException {
        int contaRegistroCorpo = 0;

        File file = new File(nomeArq + ".txt");

        // Montar o registro do Header
        String header = "00";
        header += "Abertura De Publicação";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        header += " ";
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, file);

        for (int i = 0; i < listaUserBasico.getTamanho(); i++) {
            UsuarioBasico usuarioBasico = listaUserBasico.getElemento(i);
            String corpoUserBasico = "02";
            corpoUserBasico += String.format("%-30.30s", usuarioBasico.getEmail());
            corpoUserBasico += String.format("%-30.30s", usuarioBasico.getNome());
            corpoUserBasico += String.format("%-280.280s", usuarioBasico.getSenha());
            corpoUserBasico += String.format("%-120.120s", usuarioBasico.getCargo().getNomeCargo());
            gravaRegistro(corpoUserBasico, file);
            contaRegistroCorpo++;
        }

        // Monta os registro de Corpo
        for (int i = 0; i < listaPublicacao.getTamanho() && i < listaUserBasico.getTamanho(); i++) {
            Publicacao publicacao = listaPublicacao.getElemento(i);
            String corpoPublicacao = "03";
            corpoPublicacao += String.format("%-30.30s", publicacao.getTitulo());
            corpoPublicacao += String.format("%-280.280s", publicacao.getDescricao());
            corpoPublicacao += String.format("%-120.120s", publicacao.getProposta());
            corpoPublicacao += String.format("%-50.50s", publicacao.getFkUsuario().getEmail());
            corpoPublicacao += String.format("%-30.30s", publicacao.getPlataforma());
            gravaRegistro(corpoPublicacao, file);
            contaRegistroCorpo++;
        }

        // Monta e grava o registro de trailer
        String trailer = "04";
        trailer += String.format("%05d", contaRegistroCorpo);
        gravaRegistro(trailer, file);

        BufferedReader buffer = new BufferedReader(new FileReader(file));
        StringBuilder teste = new StringBuilder();

        String str;
        while ((str = buffer.readLine()) != null) {
            teste.append(str).append("\n");
        }
        buffer.close();

        boolean value = Files.deleteIfExists(Path.of(file.getAbsolutePath()));
        if (value)
            System.out.println("JavaFile.java is successfully deleted.");
        else
            System.out.println("File doesn't exit");


        return teste;

    }

    public void importaArquivoCsvPublicacao(byte[] nomeArq, UsuarioBasicoRepository usuarioBasicoRepository,
                                            PublicacaoRepository publicacaoRepository, CargoRepository cargoRepository) {
        BufferedReader entrada = null;
        String registro, tiporegistro;
        String titulo, descricao, proposta, emailUsuario, email, nome, senha, cargo, plataforma;
        int contaRegDadoLido = 0;
        int qtdRegGravado;
        List<Publicacao> listaLida = new ArrayList<>();

        String FILEPATH = "arquivo.txt";
        File file = new File(FILEPATH);

        try {

            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));

            os.write(nomeArq);

            os.close();
        } catch (Exception e) {

            System.out.println("Exception: " + e);
        }

        try {
            entrada = new BufferedReader(new FileReader(file));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        try {
            registro = entrada.readLine();

            while (registro != null) { // Enquanto não é fim do arquivo
                tiporegistro = registro.substring(0, 2);
                switch (tiporegistro) {
                    case "00":
                        System.out.println("É um registro de header");
                        System.out.println("Tipo do arquivo: " + registro.substring(2, 23));
                        System.out.println("Data e hora de gravação do arquivo: " + registro.substring(23, 39));
                        System.out.println(" " + registro.substring(40, 40));
                        System.out.println("Versão do documento de layout: " + registro.substring(40, 42));
                        break;
                    case "04":
                        System.out.println("É um registro de trailer");
                        qtdRegGravado = Integer.parseInt(registro.substring(2, 7));
                        if (contaRegDadoLido == qtdRegGravado) {
                            System.out.println("Quantidade de registros lidos compatível com a quantidade de registros" +
                                    " gravados");
                        } else {
                            System.out.println("Quantidade de registros lidos incompatível com a quantidade de registros" +
                                    " gravados");
                        }
                        break;
                    case "03":
                        System.out.println("É um registro de corpo de Publicaco");
                        System.out.println(registro.substring(2, 32));
                        System.out.println(registro.substring(432, 482));
                        System.out.println(registro.substring(482, 512));
                        //idPublicação = Integer.valueOf(registro.substring(2, 12));
                        titulo = registro.substring(2, 32).trim();
                        descricao = registro.substring(32, 312).trim();
                        proposta = registro.substring(312, 432).trim();
                        emailUsuario = registro.substring(432, 482).trim();
                        plataforma = registro.substring(482, 512).trim();
                        contaRegDadoLido++;

                        System.out.println(emailUsuario + "///////////////////////////////////");
                        UsuarioBasico usuarioBasico = usuarioBasicoRepository.findByEmail(emailUsuario);
                        Publicacao publicacao = new Publicacao(titulo, descricao, proposta, usuarioBasico.getIdUsuario(), plataforma);

                        publicacaoRepository.save(publicacao);

                        break;
                    case "02":
                        System.out.println("É um registro do corpo de Usuario");
                        email = registro.substring(2, 32).trim();
                        nome = registro.substring(32, 62).trim();
                        senha = registro.substring(62, 312).trim();
                        cargo = registro.substring(312, 432).trim();
                        contaRegDadoLido++;

                        Cargo cargo1 = cargoRepository.findByNomeCargo(cargo);
                        UsuarioBasico usuario = new UsuarioBasico(email, nome, senha, cargo1.getIdCargo());

                        usuarioBasicoRepository.save(usuario);

                    default:
                        System.out.println("Tipo de registro inválido: " + tiporegistro);
                        break;
                }

                // Le o proximo registro
                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro na leitura do arquivo: " + erro);
        }

        file.delete();

        // Aqui, opcionalmente, pode-se importar a listaLida para o banco de dados
        // repository.saveAll(listaLida);
    }
}
