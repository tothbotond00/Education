package model;

public enum LevelItem {
    BOX('$'), BOX_IN_PLACE('*'), DESTINATION('.'), WALL('#'), EMPTY(' ');
    LevelItem(char rep){ representation = rep; }
    public final char representation;
}
