CREATE DATABASE Brianleague;

-- Team Table
CREATE TABLE team (
    id INT PRIMARY KEY,
    t_name VARCHAR(255) NOT NULL,
    venue VARCHAR(255),
    manager varchar(255),
    wins INT,
    draws INT,
    losses INT
);

-- Player Table
CREATE TABLE player (
    id INT PRIMARY KEY,
    player_name VARCHAR(255) UNIQUE NOT NULL , 
    player_number INT,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES team (id) 
);

-- Match Table
CREATE TABLE Matches (
    id INT PRIMARY KEY,
    hometeamid INT,
    awayteamid INT,
    result VARCHAR(10),
    event_id INT,
    FOREIGN KEY (hometeamid) REFERENCES team (id),
    FOREIGN KEY (awayteamid) REFERENCES team (id),
);

-- Events Table
CREATE TABLE events (
    id INT PRIMARY KEY,
    match_id INT,
    goal_id int,
    sub_id int,
    redcard_id int,
    FOREIGN KEY (match_id) REFERENCES Match (id)
);

-- Goal Table
CREATE TABLE goal (
    id INT PRIMARY KEY,
    event_id INT,
    scorer_id INT,
    timescored TIME,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (scorer_id) REFERENCES player (id)
);

-- Substitution Table
CREATE TABLE Substitution (
    id INT PRIMARY KEY,
    event_id INT,
    playeron_id INT,
    playeroff_id INT,
    timesub TIME,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (playeron_id) REFERENCES Player (id),
    FOREIGN KEY (playeroff_id) REFERENCES Player (id)
);

-- RedCard Table
CREATE TABLE RedCard (
    id INT PRIMARY KEY,
    event_id INT,
    playeroff_id INT,
    timecarded TIME,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (playeroff_id) REFERENCES Player (id)
);

-- MatchSquad Table
CREATE TABLE MatchSquad (
    id INT PRIMARY KEY,
    match_id INT,
    team_id INT,
    player_id INT,
    IsStarting BOOLEAN,
    FOREIGN KEY (match_id) REFERENCES Matches (id),
    FOREIGN KEY (team_id) REFERENCES Team (id),
    FOREIGN KEY (player_id) REFERENCES Player (id)
);

-- Insertions

-- Inserting values into the "team" table
INSERT INTO team (id, t_name, venue, manager, points)
VALUES
    (1, 'Liverpool', 'Anfield', 'Jurgen Klopp', 60),
    (2, 'Ireland', 'Aviva Stadium', 'Stephen Kenny', 30),
    (3, 'Galway United', 'Eamonn Deacy Park', 'John Caulfield', 15),
    (4, 'Ballina Town FC', 'Belleek Park', 'Alan Reilly', 20);

-- Inserting values into the "Player" table
INSERT INTO Player (id, player_name, player_number, team_id)
VALUES
    (101, 'Steven Gerrard', 8, 1),
    (102, 'Mohamed Salah', 11, 1),
    (103, 'Virgil van Dijk', 4, 1),
    (201, 'Robbie Keane', 7, 2),
    (202, 'James McClean', 10, 2),
    (203, 'Shane Long', 9, 2),
    (301, 'David Cawley', 2, 3),
    (302, 'Grank Flavin', 15, 3),
    (303, 'Colm TheBest', 23, 3),
    (401, 'John Doe', 1, 4),
    (404, 'Joe Bloggs', 12, 4),
    (403, 'Brian Moyles', 67, 4);

-- Inserting values into the "Match" table
INSERT INTO Match (id, hometeamid, awayteamid, result, event_id)
VALUES
    (1, 1, 2, '3-1', 101),
    (2, 3, 4, '2-2', 102),
    (3, 1, 3, '2-0', 103);

-- Inserting values into the "Events" table
INSERT INTO Events (id, match_id, goal_id, sub_id, redcard_id)
VALUES
    (101, 1, 201, NULL, NULL),
    (102, 1, 202, NULL, NULL),
    (103, 2, NULL, 301, NULL);

-- Inserting values into the "Goal" table
INSERT INTO Goal (id, event_id, scorer_id, timescored)
VALUES
    (201, 101, 102, '12:34:00'),
    (202, 102, 201, '23:45:00'),
    (203, 103, 302, '30:15:00');

-- Inserting values into the "Substitution" table
INSERT INTO Substitution (id, event_id, playeron_id, playeroff_id, timesub)
VALUES
    (301, 103, 302, 301, '55:00:00'),
    (302, 103, 303, 302, '65:00:00'),
    (303, 102, 203, 202, '75:00:00');

-- Inserting values into the "RedCard" table
INSERT INTO RedCard (id, event_id, playeroff_id, timecarded)
VALUES
    (401, 101, 202, '10:00:00'),
    (402, 102, 202, '25:00:00'),
    (403, 103, 301, '60:00:00');

-- Inserting values into the "MatchSquad" table
INSERT INTO MatchSquad (id, match_id, team_id, player_id, IsStarting)
VALUES
    (501, 1, 1, 101, true),
    (502, 1, 1, 102, true),
    (503, 2, 1, 103, true),
    (504, 2, 2, 201, true),
    (505, 2, 2, 202, true),
    (506, 2, 2, 203, true),
    (507, 3, 1, 101, true),
    (508, 3, 1, 103, true),
    (509, 3, 3, 302, true),
    (510, 3, 3, 303, true);

-- Queries 

-- 1
SELECT * 
FROM player
WHERE team_id = (SELECT id FROM team WHERE t_name = 'Liverpool');

-- 2
SELECT player.player_name
FROM player
INNER JOIN goal ON player.id = goal.scorer_id
WHERE goal.event_id = 102;

-- 3
SELECT player.player_name, COUNT(goal.id) AS goals_scored
FROM player
LEFT JOIN goal ON player.id = goal.scorer_id
GROUP BY player.id
ORDER BY goals_scored DESC
LIMIT 5;

-- 4
select t_name AS team_name, SUM((wins*3) + (draws)) AS points
from team
Group by t_name;