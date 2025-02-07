public class Administrador {
    private String id;
    private String contrasena;

    public Administrador(String id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    // Método para validar credenciales
    public boolean validarCredenciales(String id, String contrasena) {
        return this.id.equals(id) && this.contrasena.equals(contrasena);
    }

    @Override
    public String toString() {
        return id + "," + contrasena;
    }
}
