package model;

public class FreeRoom extends Room{
    private String roomNumber;
    private Double price;
    private RoomType enumeration;


    public FreeRoom(String roomNumber, RoomType enumeration){
        super(roomNumber, 0.0, enumeration);
    }
}
