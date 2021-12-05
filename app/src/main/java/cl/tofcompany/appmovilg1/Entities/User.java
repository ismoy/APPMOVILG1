package cl.tofcompany.appmovilg1.Entities;

public class User {
    String id;
    String nombrecompleto;
    String email;
    String password;
    String telefono;
    String fechanacimiento;

    public User() {
    }

    public User(String id, String nombrecompleto, String email, String password, String telefono,String fechanacimiento) {
        this.id = id;
        this.nombrecompleto = nombrecompleto;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechanacimiento = fechanacimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}
