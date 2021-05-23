package GreedIsland;

import GreedIsland.Items.Hero;

import java.sql.*;

public class Database
{
    private RefLinks refLink;

    public Database(RefLinks ref) { this.refLink = ref; }

    public void updateDatabase()
    {
        Connection c = null;
        Statement stmt = null;


        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GreedIslandDB.db");
        }
        catch(Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try
        {
            /*
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS GreedIslandDBTable " +
                    "(Level INT NOT NULL, " +
                    "Score INT NOT NULL, " +
                    "EnemiesKilled INT NOT NULL, " +
                    "ChestsCollected INT NOT NULL, " +
                    "Status TEXT NOT NULL)";
            stmt.executeUpdate(sql);
             */

            if(refLink.GetGame().gameWon)
            {
                stmt = c.createStatement();

                String sql = "DELETE FROM GreedIslandDBTable " +
                        "WHERE Level = 1";
                stmt.executeUpdate(sql);

                sql = "DELETE FROM GreedIslandDBTable " +
                        "WHERE Level = 2";
                stmt.executeUpdate(sql);

                 sql = "INSERT INTO GreedIslandDBTable (Level, Score, EnemiesKilled, ChestsCollected, Status) " +
                        "VALUES (" + refLink.GetGame().level + ", " + refLink.GetMap().score + ", " +
                        Hero.getHeroInstance(refLink, 0, 0).nrEnemiesKilled + ", " + Hero.getHeroInstance(refLink, 0, 0).nrChestsCollected +
                        ", 'WIN');";
                stmt.executeUpdate(sql);

                stmt.close();
                refLink.GetGame().gameWon = false; // ca sa se execute doar o data update-ul in baza de date
            }

            if(refLink.GetGame().gameLost)
            {
                stmt = c.createStatement();

                String sql = "DELETE FROM GreedIslandDBTable " +
                        "WHERE Level = 1";
                stmt.executeUpdate(sql);

                sql = "DELETE FROM GreedIslandDBTable " +
                        "WHERE Level = 2";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO GreedIslandDBTable (Level, Score, EnemiesKilled, ChestsCollected, Status) " +
                        "VALUES (" + refLink.GetGame().level + ", " + refLink.GetMap().score + ", " +
                        Hero.getHeroInstance(refLink, 0, 0).nrEnemiesKilled + ", " + Hero.getHeroInstance(refLink, 0, 0).nrChestsCollected +
                        ", 'LOSE');";
                stmt.executeUpdate(sql);

                stmt.close();
                refLink.GetGame().gameLost = false; // ca sa se execute doar o data update-ul in baza de date
            }

            c.commit();
            c.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Exceptie la creare statement!");
        }
    }

    public String getDatabaseInformation()
    {
        Connection c = null;
        Statement stmt = null;

        String rez = "";

        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GreedIslandDB.db");
        }
        catch(Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try
        {
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM GreedIslandDBTable;");
            while(rs.next())
            {
                int level = rs.getInt("Level");
                int score = rs.getInt("Score");
                int enemiesKilled = rs.getInt("EnemiesKilled");
                int chestsCollected = rs.getInt("ChestsCollected");
                String status = rs.getString("Status");

                rez += "   " + level + "         " + score + "                    " + enemiesKilled + "                         " + chestsCollected + "                 " + status + "       ";
            }

            c.commit();
            c.close();
        }
        catch(SQLException ex)
        {
            //System.out.println("Exceptie la creare statement!");
        }

        return rez;
    }


}
