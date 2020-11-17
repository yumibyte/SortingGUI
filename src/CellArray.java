import java.awt.Color;
import java.awt.Graphics;

class CellArray {
    int[] numbers;
    int numcells;
    int[] bgcolors;
    int[] fgcolors;
    int occupiedcells;
    int cellheight;
    int cellwidth;
    int startx;
    int starty;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int RED = 2;
    public static final int BLUE = 3;
    public static final int YELLOW = 4;
    public static final int ORANGE = 5;
    public static final int GREEN = 6;
    public static final int GRAY = 7;
    public static final int PINK = 8;

    public CellArray(int var1, int var2, int var3, int var4, int var5) {
        this.numcells = var1;
        this.numbers = new int[var1];
        this.bgcolors = new int[var1];
        this.fgcolors = new int[var1];

        for(int var6 = 0; var6 < var1; ++var6) {
            this.fgcolors[var6] = 1;
        }

        this.occupiedcells = 0;
        this.cellheight = var2;
        this.cellwidth = var3;
        this.startx = var4;
        this.starty = var5;
    }

    public void paint(Graphics var1) {
        this.paintGrid(var1);
        this.paintBgColors(var1);
        this.paintContents(var1);
    }

    public void paintGrid(Graphics var1) {
        int var2 = this.startx;
        int var3 = this.starty;
        var1.setColor(Color.black);
        int var4 = this.cellheight * 3 / 4;

        for(int var5 = 0; var5 < this.numcells; ++var5) {
            var1.drawString(var5 + "", var2, var3 + var4);
            var1.drawLine(var2 + 20, var3, var2 + 20, var3 + this.cellheight);
            var1.drawLine(var2 + 20 + this.cellwidth, var3, var2 + 20 + this.cellwidth, var3 + this.cellheight);
            var1.drawLine(var2 + 20, var3, var2 + 20 + this.cellwidth, var3);
            var3 += this.cellheight;
        }

        var1.drawLine(var2 + 20, var3, var2 + 20 + this.cellwidth, var3);
    }

    public void paintBgColors(Graphics var1) {
        int var2 = this.startx + 20;
        int var3 = this.starty;

        for(int var4 = 0; var4 < this.numcells; ++var4) {
            var1.setColor(this.translate(this.bgcolors[var4]));
            var1.fillRect(var2 + 1, var3 + 1, this.cellwidth - 1, this.cellheight - 1);
            var3 += this.cellheight;
        }

    }

    public void paintContents(Graphics var1) {
        int var2 = this.startx + 20;
        int var3 = this.starty;

        for(int var4 = 0; var4 < this.numcells; ++var4) {
            var1.setColor(this.translate(this.fgcolors[var4]));
            var1.drawString(this.numbers[var4] + "", var2 + 5, var3 + this.cellheight - 5);
            var3 += this.cellheight;
        }

    }

    public void set(int var1, int var2) {
        if (var1 >= 0 && var1 < this.numcells) {
            this.numbers[var1] = var2;
        }
    }

    public void setFgColor(int var1, int var2) {
        if (var1 >= 0 && var1 < this.numcells) {
            this.fgcolors[var1] = var2;
        }
    }

    public void setBgColor(int var1, int var2) {
        if (var1 >= 0 && var1 < this.numcells) {
            this.bgcolors[var1] = var2;
        }
    }

    public void unsetBgColors() {
        for(int var1 = 0; var1 < this.numcells; ++var1) {
            this.bgcolors[var1] = 0;
        }

    }

    public void clear() {
        this.occupiedcells = 0;

        int var1;
        for(var1 = 0; var1 < this.numcells; ++var1) {
            this.numbers[var1] = 0;
        }

        for(var1 = 0; var1 < this.numcells; ++var1) {
            this.fgcolors[var1] = 1;
        }

        for(var1 = 0; var1 < this.numcells; ++var1) {
            this.bgcolors[var1] = 0;
        }

    }

    public void add(int var1) {
        if (this.occupiedcells >= 0 && this.occupiedcells < this.numcells) {
            this.numbers[this.occupiedcells++] = var1;
        }
    }

    public Color translate(int var1) {
        switch(var1) {
            case 0:
                return Color.white;
            case 1:
                return Color.black;
            case 2:
                return Color.red;
            case 3:
                return Color.blue;
            case 4:
                return Color.yellow;
            case 5:
                return Color.orange;
            case 6:
                return Color.green;
            case 7:
                return Color.lightGray;
            case 8:
                return Color.pink;
            default:
                return Color.white;
        }
    }
}