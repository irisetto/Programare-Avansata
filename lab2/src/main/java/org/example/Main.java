package org.example;

enum Locations{
    city,
    airport,
    gas_station
}
enum Roads{
    highway,
    express,
    country
}

class Location{
    private String name;
    private Locations type;
    private Double x;
    private Double y;

    public Location(String name,Locations type,Double x,Double y)
    {
        this.name=name;
        this.type=type;
        this.x=x;
        this.y=y;
    }
    public String getName()
    {
        return name;
    }

    public Locations getType()
    {
        return type;
    }

    public Double getX()
    {
        return x;
    }

    public Double getY()
    {
        return y;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setType(Locations type)
    {
        this.type = type;
    }

    public void setX(Double x)
    {
        this.x = x;
    }

    public void setY(Double y)
    {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
class Road{
    private Roads type;
    private Double length;
    private Integer speedLimit;
    public Road(Location l1,Location l2,Roads type,Double length,Integer speedLimit)
    {
        //length should not be less than √[(x2 – x1)^2 + (y2 – y1)^2]
        Double distance=Math.sqrt(Math.pow(l2.getX()- l1.getX(),2)+Math.pow(l2.getY()- l1.getY(),2));
        this.type=type;

        if(length<distance)
        {
            System.out.println("The length of the road is less than the euclidian distance between the location coordinates");
            return;
        }
          else  this.length=length;

        this.speedLimit=speedLimit;
    }
    public Roads getType()
    {
        return type;
    }

    public Double getLength()
    {
        return length;
    }

    public Integer getSpeedLimit()
    {
        return speedLimit;
    }

    public void setType(Roads type)
    {
        this.type = type;
    }

    public void setLength(Double length)
    {
        this.length = length;
    }

    public void setSpeedLimit(Integer speedLimit)
    {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "Road{" +
                "type=" + type +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                '}';
    }
}
public class Main {
    public static void main(String[] args) {
        Location loc1=new Location("Bucuresti",Locations.city,44.4361414,26.1027202);
        Location loc2=new Location("Iasi",Locations.city,47.174023500000004,27.57485865552524);

        Road road1=new Road(loc1,loc2,Roads.country,2000.0,30);
        System.out.println(loc1);
        System.out.println(road1);
        System.out.println(road1.getLength());
        road1.setType(Roads.express);
        System.out.println(road1.getType());
    }
}