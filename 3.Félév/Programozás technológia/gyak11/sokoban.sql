CREATE DATABASE IF NOT EXISTS sokoban;
USE sokoban;
CREATE TABLE IF NOT EXISTS HighScore (
  Difficulty VARCHAR(50) NOT NULL,
  GameLevel  INT NOT NULL,
  Steps      INT,
  PRIMARY KEY(Difficulty, GameLevel)
);