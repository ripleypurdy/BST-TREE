public class Move {
    private int location;
    private String piece;
    public Move(int location, String piece) {
        this.location = location;
        this.piece = piece;
    }

    public int location() {
        return location;
    }

    public String piece() {
        return piece;
    }

    public boolean equals(Object other) {
        Move m = (Move) other;
        return this.piece.equals(m.piece) && this.location == m.location;
    }

    public String toString() {
        return String.format("location: %d piece: %s", location, piece);
}
}
