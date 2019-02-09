package sg.edu.np.s10177744connect.madassignment;

public class Country {


    public  String Photo;
    public  String Place;
    public  String Num;
    public  String Attraction;
    public  String Address;
    public  String Details;
    public  String Status;
    public  String Countryname;
    public  String Visit;
    public int id;
    public byte[] image;



    public  String Lag;
    public String Long;

    public Country() {}

    public Country(String a, String d, byte[] i, int Id) {
        Attraction = a;
        Details = d;
        image = i;
        id = Id;
    }

    public Country(String i ,String a, String d, String p, String s, String l) {
        Num = i;
        Attraction = a;
        Address = l;
        Details = d;
        Photo = p;
        Status = s;
    }
    public Country(int id, String a, String l)
    {
        this.id = id;
        Attraction = a;
        Address = l;
    }
    public Country(String a,String s)
    {
        Attraction = a;
        Status = s;
    }
    public Country(String a,String s, byte[] i)
    {
        Attraction = a;
        Status = s;
        image = i;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAttraction() {
        return Attraction;
    }
    public void setAttraction(String Attraction) {this.Attraction = Attraction; }
    public String getAddress() { return Address; }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getPhoto() { return Photo; }
    public void setPhoto(String photo) { Photo = photo; }
    public String getStatus() { return Status; }
    public void setStatus(String status) { Status = status; }
    public String getDetails() { return Details; }
    public void setDetails(String details) { Details = details; }
    public String getCountryname() { return Countryname; }
    public void setCountryname(String countryname) { Countryname = countryname; }
    public String getVisit() { return Visit; }
    public void setVisit(String visit) { Visit = visit; }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getLag() { return Lag; }
    public void setLag(String lag) { Lag = lag; }
    public String getLong() { return Long; }
    public void setLong(String aLong) { Long = aLong; }

}

