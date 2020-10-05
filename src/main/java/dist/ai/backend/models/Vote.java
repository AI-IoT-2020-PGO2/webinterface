package dist.ai.backend.models;

public class Vote {
    byte score;
    int userID;
    String timestamp;
    int songID;

    public Vote() {
    }

    public Vote(byte score, int userID, String timestamp, int songID) {
        this.score = score;
        this.userID = userID;
        this.timestamp = timestamp;
        this.songID = songID;
    }

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
}
