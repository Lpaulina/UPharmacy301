public class User {
    private String role;
    private int ID;
    private String name;

    public User(){
        role = "None";
        ID = -1;
        name = "None";
    }

    public void setID(int id){
        ID = id;
    }
    
    public int getID(){
        return ID;
    }

    public void setRole(String userRole){
        role = userRole;
    }

    public String getRole(){
        return role;
    }

    public void setName(String userName){
        name = userName;
    }
    public String getName(){
        return name;
    }
}
