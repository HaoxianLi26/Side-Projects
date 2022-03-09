package model;

public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }


    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    /**
     * If the price of the room is 0, that is a free room
     * @return return true if the price is 0
     */
    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString(){
        return "Room Number: " + roomNumber + ", Room Price: "
                + price + ", Room Type: " + enumeration.toString();
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Room)){
            return false;
        }
        Room o = (Room)other;
        return o.roomNumber.equals(this.roomNumber);
    }

    @Override
    public int hashCode(){
        return this.roomNumber.hashCode();
    }
}
