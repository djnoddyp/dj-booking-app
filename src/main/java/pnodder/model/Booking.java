package pnodder.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Booking {
    
    private Integer id;

    @Size(min = 1, max = 16)
    private String name;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull @Future
    private LocalDate date;
    
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    private LocalTime start;
    
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    private LocalTime finish;

    @NotEmpty
    private Set<Artist> artists;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Double cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getFinish() {
        return finish;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
