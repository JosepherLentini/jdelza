package com.jdelza.model.entities;

import java.util.Objects;

/**
 * Position represent the position in the map
 * the position is indicated by zone coordinates and cell coordinates
 * Neither the zone coordinate alone nor the cell coordinate alone is sufficient:
 * every object on the map is located in a zone and, within that zone, in a cell.
 * Both are therefore required.
 */
public class Position {
    private Coordinates zoneCoordinates;
    private Coordinates cellCoordinates;

    public Position(Coordinates zoneCoordinates, Coordinates cellCoordinates) {
        this.zoneCoordinates = zoneCoordinates;
        this.cellCoordinates = cellCoordinates;
    }


    public Coordinates getZoneCoordinates() {
        return zoneCoordinates;
    }

    public Coordinates getCellCoordinates() {
        return cellCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(zoneCoordinates, position.zoneCoordinates) && Objects.equals(cellCoordinates, position.cellCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneCoordinates, cellCoordinates);
    }

    @Override
    public String toString(){
        return getZoneCoordinates().toString() + " | " + getCellCoordinates().toString();
    }
}
