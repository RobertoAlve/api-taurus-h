package com.taurusmagister.taurusmagister.controller;

import com.taurusmagister.taurusmagister.entidade.*;
import com.taurusmagister.taurusmagister.repositorio.*;
import com.taurusmagister.taurusmagister.resposta.UsuarioAdmConsulta;
import com.taurusmagister.taurusmagister.resposta.UsuarioBasicoConsulta;
import com.taurusmagister.taurusmagister.resposta.UsuarioBasicoInfoAmigos;
import com.taurusmagister.taurusmagister.resposta.UsuarioBasicoLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioBasicoRepository usuarioBasicoRepository;

    @Autowired
    private UsuarioAdmRepository usuarioAdmRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    ManipularCsv manipularCsv = new ManipularCsv();

    @PostMapping
    public ResponseEntity<Void> cadastraUsuario(@RequestBody UsuarioBasico usuario) {
        if (usuarioBasicoRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        usuarioBasicoRepository.save(usuario);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioBasicoLogin> getUsuarioPorEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(usuarioBasicoRepository.getUsuarioByEmail(email));
    }

    @PostMapping("/autenticacao/{email}/{senha}")
    public ResponseEntity<Void> autenticaUsuario(@PathVariable String email, @PathVariable String senha) {
        UsuarioBasico usuarioBasicoEncontrado = usuarioBasicoRepository.findByEmailAndSenha(email, senha);
        UsuarioAdm usuarioAdmEncontrado = usuarioAdmRepository.findByEmailAndSenha(email, senha);

        if (usuarioBasicoEncontrado != null) {
            if (!usuarioBasicoEncontrado.isAutenticado()) {
                usuarioBasicoRepository.autentica(usuarioBasicoEncontrado.getEmail(), true);
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(409).build();
            }
        } else {
            if (usuarioAdmEncontrado != null) {
                if (!usuarioAdmEncontrado.isAutenticado()) {
                    usuarioAdmRepository.autentica(usuarioAdmEncontrado.getEmail(), true);
                    return ResponseEntity.status(200).build();
                } else {
                    return ResponseEntity.status(409).build();
                }
            }
        }

        return ResponseEntity.status(401).build();
    }

    @DeleteMapping("/autenticacao/{email}")
    public ResponseEntity<Void> logoff(@PathVariable String email) {
        UsuarioBasicoLogin usuarioBasicoEncontrado = usuarioBasicoRepository.getUsuarioByEmail(email);
        UsuarioAdm usuarioAdmEncontrado = usuarioAdmRepository.findByEmail(email);

        if (usuarioBasicoEncontrado != null) {
            if (usuarioBasicoEncontrado.isAutenticado()) {
                usuarioBasicoRepository.logOff(usuarioBasicoEncontrado.getEmail(), false);
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(401).build();
            }
        } else {
            if (usuarioAdmEncontrado != null) {
                if (usuarioAdmEncontrado.isAutenticado()) {
                    usuarioAdmRepository.logOff(usuarioAdmEncontrado.getEmail(), false);
                    return ResponseEntity.status(200).build();
                } else {
                    return ResponseEntity.status(401).build();
                }
            }
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/logado/{email}")
    public ResponseEntity<Boolean> getUsuarioLogado(@PathVariable String email) {

        if (usuarioBasicoRepository.existsByEmail(email)) {
            UsuarioBasicoLogin usuario = usuarioBasicoRepository.getUsuarioByEmail(email);
            if (usuario.isAutenticado()) {
                return ResponseEntity.status(200).body(true);
            } else {
                return ResponseEntity.status(200).body(false);
            }
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping
    public ResponseEntity exibeUsuarios() {
        List<Object> usuarios = new ArrayList<>();
        usuarios.add(usuarioBasicoRepository.getUsuariosBasicos());
        usuarios.add(usuarioAdmRepository.getUsuariosAdm());
        if (usuarios.size() == 0) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @GetMapping("/autenticacao")
    public ResponseEntity exibeAutenticados() {
        List<Object> usuariosAutenticados = new ArrayList<>();
        usuariosAutenticados.add(usuarioBasicoRepository.findAll()
                .stream().filter(Usuario::isAutenticado)
                .collect(Collectors.toList()));

        usuariosAutenticados.add(usuarioAdmRepository.findAll()
                .stream().filter(Usuario::isAutenticado)
                .collect(Collectors.toList()));

        if (usuariosAutenticados.size() == 0) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuariosAutenticados);
        }
    }

    @PostMapping("/adm")
    public ResponseEntity cadastraAdm(@RequestBody UsuarioAdm usuarioAdm) {
        if (usuarioAdmRepository.existsByEmail(usuarioAdm.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        usuarioAdmRepository.save(usuarioAdm);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/adm")
    public ResponseEntity exibeUsuariosAdm() {
        List<UsuarioAdmConsulta> usuariosAdm = usuarioAdmRepository.getUsuariosAdm();
        if (usuariosAdm.size() == 0) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuariosAdm);

    }

    @GetMapping("/informacoes/{email}")
    public ResponseEntity exibeInformacoes(@PathVariable String email) {
        List<UsuarioAdmConsulta> usuarioAdm = usuarioAdmRepository.getUsuariosAdm();
        List<UsuarioBasicoConsulta> usuarioBasico = usuarioBasicoRepository.getUsuariosBasicos();
        List<Object> usuarios = new ArrayList<>();
        usuarios.add(usuarioAdm);
        usuarios.add(usuarioBasico);

        System.out.println(usuarioBasico);

        UsuarioAdm usuarioAdmEncontrado = usuarioAdmRepository.findByEmail(email);
        UsuarioBasico usuarioBasicoEncontrado = usuarioBasicoRepository.findByEmail(email);

        if (usuarioBasico.size() <= 0 && usuarioAdm.size() == 0) {
            return ResponseEntity.status(204).build();
        } else {
            if (usuarioBasicoEncontrado != null) {
                return ResponseEntity.status(200).body(usuarioBasicoEncontrado.exibeInformacoes(usuarios));
            } else {
                if (usuarioAdmEncontrado != null) {
                    return ResponseEntity.status(200).body(usuarioAdmEncontrado.exibeInformacoes(usuarios));
                }
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/gravarCsv/{nomeArq}")
    public ResponseEntity gravaArquivoCsv(@PathVariable String nomeArq) {
        ListaObj<UsuarioBasico> usuarios = new ListaObj<>(usuarioBasicoRepository.findAll().size());

        for (UsuarioBasico usuarioBasico : usuarioBasicoRepository.findAll()) {
            usuarios.adiciona(usuarioBasico);
        }

        manipularCsv.gravaArquivoCsv(usuarios, nomeArq);
        return ResponseEntity.status(200).build();
    }

    @PostMapping(value="/lerCsv", consumes = "text/csv")
    public ResponseEntity leArquivoCsv(@RequestBody byte[] file) {
        manipularCsv.leArquivoCsv(file);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/relatorio-csv")
    public ResponseEntity getRealatorioCsv() {
        StringBuilder relatorio = new StringBuilder();

        List<UsuarioBasicoConsulta> lista = usuarioBasicoRepository.getUsuariosBasicos();
        for (UsuarioBasicoConsulta usuario : lista) {
            relatorio.append(usuario.getNome()).append(";").append(usuario.getEmail()).append("\r\n");
        }
        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relaorio-de-usuarios-basicos.csv\"")
                .body(relatorio.toString());
    }

    @GetMapping("/exportaArquivo/{nomeArq}")
    public ResponseEntity exportaArquivo(@PathVariable String nomeArq) throws IOException {

        ListaObj<UsuarioBasico> usuarioBasicoListaObj = new ListaObj<>(usuarioBasicoRepository.findAll().size());

        for (UsuarioBasico usuarioBasico : usuarioBasicoRepository.findAll()) {
            usuarioBasicoListaObj.adiciona(usuarioBasico);
        }

        ListaObj<Publicacao> publicacaoListaObj = new ListaObj<>(publicacaoRepository.findAll().size());

        for (Publicacao publicacao : publicacaoRepository.findAll()) {
            publicacaoListaObj.adiciona(publicacao);
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/txt")
                .header("content-disposition", String.format("attachment; filename=\"%s.txt\"", nomeArq))
                .body( manipularCsv.exportaArquivoTxt(usuarioBasicoListaObj, publicacaoListaObj, nomeArq));

        //return ResponseEntity.status(404).build();
    }

    @PostMapping(value = "/importaArquivo", consumes = "text/plain")
    public ResponseEntity importaCsv(@RequestBody byte[] file) {

        manipularCsv.importaArquivoCsvPublicacao(file, usuarioBasicoRepository,
                publicacaoRepository, cargoRepository);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/update/area/{nomeArea}/{idUsuario}")
    public ResponseEntity updateArea(@PathVariable String nomeArea,
                                     @PathVariable int idUsuario) {
        Area area = new Area();
        area.setNomeArea(nomeArea);
        areaRepository.save(area);
        List<Area> areasEncontradas = areaRepository.getArea(idUsuario);
        Pilha<Area> areas = new Pilha<>(areasEncontradas.size());
        for (Area a : areasEncontradas) {
            areas.push(a);
        }
        usuarioBasicoRepository.updateSqlArea(areas.peek().getIdArea(), idUsuario);

        return ResponseEntity.status(200).build();
    }

    @PutMapping("/update/cargo/{nomeCargo}/{idUsuario}")
    public ResponseEntity updateCargo(@PathVariable String nomeCargo,
                                      @PathVariable int idUsuario) {
        Cargo cargo = new Cargo();
        cargo.setNomeCargo(nomeCargo);
        cargoRepository.save(cargo);
        List<Cargo> cargosEncontrados = cargoRepository.getCargos(idUsuario);
        Pilha<Cargo> cargos = new Pilha<>(cargosEncontrados.size());
        for (Cargo c : cargosEncontrados) {
            cargos.push(c);
        }
        usuarioBasicoRepository.updateSqlCargo(cargos.peek().getIdCargo(), idUsuario);

        return ResponseEntity.status(200).build();
    }

    @GetMapping("areas")
    public ResponseEntity getAreas() {
        return ResponseEntity.status(200).body(areaRepository.findAll());
    }

    @GetMapping("cargo")
    public ResponseEntity getCargo() {
        return ResponseEntity.status(200).body(cargoRepository.findAll());
    }

    @PatchMapping(value = "/foto/{codigo}", consumes = {"image/jpeg", "image/jpg", "image/jfif"})
    public ResponseEntity<Void> patchFoto(@PathVariable int codigo, @RequestBody byte[] novaFoto) {
        if (!usuarioBasicoRepository.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        UsuarioBasico userEncontrado = usuarioBasicoRepository.findById(codigo).get();
        userEncontrado.setImagem(novaFoto);
        usuarioBasicoRepository.save(userEncontrado);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/foto/{codigo}", produces = {"image/jpeg", "image/jpg", "image/jfif"})
    public ResponseEntity<byte[]> getFoto(@PathVariable int codigo) {
        if (!usuarioBasicoRepository.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        UsuarioBasico userEncontrado = usuarioBasicoRepository.findById(codigo).get();
        return ResponseEntity.status(200).body(userEncontrado.getImagem());
    }

    @PatchMapping(value = "/fotoCapa/{codigo}", consumes = {"image/jpeg", "image/jpg", "image/jfif"})
    public ResponseEntity<Void> patchFotoCapa(@PathVariable int codigo, @RequestBody byte[] novaFoto) {
        if (!usuarioBasicoRepository.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        UsuarioBasico userEncontrado = usuarioBasicoRepository.findById(codigo).get();
        userEncontrado.setImagemCapa(novaFoto);
        usuarioBasicoRepository.save(userEncontrado);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/fotoCapa/{codigo}", produces = {"image/jpeg", "image/jpg", "image/jfif"})
    public ResponseEntity<byte[]> getFotoCapa(@PathVariable int codigo) {
        if (!usuarioBasicoRepository.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        UsuarioBasico userEncontrado = usuarioBasicoRepository.findById(codigo).get();
        return ResponseEntity.status(200).body(userEncontrado.getImagemCapa());
    }

    @PostMapping("/conferencia/{idUsuario}/{idConferencia}")
    public ResponseEntity<Integer> updateIdConferenciaUsuario(@PathVariable int idUsuario, @PathVariable String idConferencia) {
        usuarioBasicoRepository.alterarIdConferencia(idUsuario, idConferencia);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/conferencia/{idUsuario}")
    public ResponseEntity<String> getIdConferenciaUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.status(200).body(usuarioBasicoRepository.getIdConferencia(idUsuario));
    }

    @GetMapping("/amigos/{idUsuario}")
    public ResponseEntity<List<UsuarioBasicoInfoAmigos>> getAmigosUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.status(200).body(usuarioBasicoRepository.getAmigosUsuario(idUsuario));
    }

    @PostMapping("/amigos/{idUsuario}/{idAmigo}")
    public ResponseEntity postAmigoUsuario(@PathVariable int idUsuario, @PathVariable int idAmigo) {
        UsuarioBasico usuarioBasico = usuarioBasicoRepository.getById(idUsuario);
        UsuarioBasico amigo = usuarioBasicoRepository.getById(idAmigo);

        usuarioBasico.adicionarAmigo(amigo);

        usuarioBasicoRepository.save(usuarioBasico);

        return ResponseEntity.status(200).build();
    }
}
