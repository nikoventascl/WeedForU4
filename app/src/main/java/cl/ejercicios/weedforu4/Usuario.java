package cl.ejercicios.weedforu4;

public class Usuario {
    int id;
    String Email, Password;

    public Usuario() {
    }

    public Usuario(int id, String email, String password) {
        this.id = id;
        Email = email;
        Password = password;
    }

    public boolean isNull(){
        if (Email.equals("")&&Password.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
