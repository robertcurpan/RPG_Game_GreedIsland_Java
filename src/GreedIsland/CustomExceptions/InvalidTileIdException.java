package GreedIsland.CustomExceptions;

public class InvalidTileIdException extends Exception
{
    private String message;

    public InvalidTileIdException(String messageString)
    {
        this.message = messageString;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
