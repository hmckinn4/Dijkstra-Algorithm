package com.solvd.fastestalgo.binary;

public class User {
    private int id;
    private String name;
    private String startingTown;
    private String destinationTown;
    private String path;

        public User(int id, String name, String startingTown, String destinationTown, String path) {
            this.id = id;
            this.name = name;
            this.startingTown = startingTown;
            this.destinationTown = destinationTown;
            this.path = path;
        }

        // Add this method to your User class
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", startingTown='" + startingTown + '\'' +
                    ", destinationTown='" + destinationTown + '\'' +
                    ", path='" + path + '\'' +
                    '}';
        }

        // Getter and setter methods omitted for brevity

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartingTown() {
        return startingTown;
    }

    public void setStartingTown(String startingTown) {
        this.startingTown = startingTown;
    }

    public String getDestinationTown() {
        return destinationTown;
    }

    public void setDestinationTown(String destinationTown) {
        this.destinationTown = destinationTown;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

