# Shadow Blocks

The original project submitted for the `SWEN20003 - Object Oriented Software Development` course in University of Melbourne, second half of 2017.

**Game Design by**: Eleanor McMurtry (http://github.com/noneuclideangirl)

The specs only told us to make a simple 'Sokoban' game with 5 levels, but I extended the project to have 60 levels instead of just 5:

### Added Feature List

#### Minor Features

- Main Menu and Level Select screen. (The GUI code is really hack-ish because I am too lazy to write actual GUI code.)
- Animation if you die to certain kinds of enemies (mage and shadow).
- Level finish and failure screens.

#### Major Features (in order of appearance):

- Independent switches for better puzzles.
- New enemy - _Shadow_:
  - Mirrors your moves and pushes blocks as well.
  - Synchronizes his movement with yours.
- New Tiles - _Auto-Blocks_ and _Conveyor Belts_:
  - A block that automatically moves.
  - Conveyor belts that moves the blocks on top of it.
- New Enemy - _Lasers_:
  - Hitting the lasers kill you.
  - Use walls or other solid things as defense.
- New Tile - _Disappearing Stone_:
  - Stones that disappear periodically. Making it easier for you to screw up the level **without you knowing it**.
- New Tiles - _Inscribed Stones_:
  - Stones that has letters or an inscribed message that can only be matched with the corresponding target.
- New Player - _Donald Trump_:
  - Play as Donald Trump, and build a wall to help you succeed.

### Will you ever update this?

If by 'this' you mean the repository then never. However, I might be inclined to update the game some time - but if I ever do so, I will create a fork of the game just so the authenticity of the original submission is kept legit.

### I wanna make my own levels

Go nuts, the file format is a csv file. I am too lazy to write a guide for it at this moment.

Note that the current number of levels is hardcoded as a constant into the game. If you need to add more then you will need to change it as well as otherwise you won't be able to access your new level.