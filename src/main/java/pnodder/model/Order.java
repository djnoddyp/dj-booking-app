package pnodder.model;

public class Order {

    private Integer id;
    private String email;
    private Bike bike;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    @Override
    public String toString() {
        if (getBike() != null) {
            return getEmail() + "," + getBike().toString();    
        } else {
            return null;
        }
        
    }
}
