package cleancode.minesweeper.tobe.minesweaper.gameLevel;

public class Advanced implements GameLevel{

    @Override
    public int getRowSize() {
        return 120;
    }

    @Override
    public int getColSize() {
        return 124;
    }

    @Override
    public int getLandMineCount() {
        return
                9;
    }
}
