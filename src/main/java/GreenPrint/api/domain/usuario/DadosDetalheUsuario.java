package GreenPrint.api.domain.usuario;

public record DadosDetalheUsuario(
        Long id,
        String nome,
        String email,
        String telefone,
        Papel papel
) {
    // Construtor que recebe um Usuario e transforma em DTO
    public DadosDetalheUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getPapelEnum());
    }
}
