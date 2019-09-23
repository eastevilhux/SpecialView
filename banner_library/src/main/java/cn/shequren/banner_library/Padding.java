package cn.shequren.banner_library;

/**
 * @author Loren
 * Create_Time: 2019/9/23 9:28
 * description:
 */
public class Padding {

    private int paddingTop;
    private int paddingLeft;
    private int paddingRight;
    private int paddingBottom;

    public Padding(int horizontal, int vertical) {
        this.paddingTop = vertical;
        this.paddingBottom = vertical;
        this.paddingLeft = horizontal;
        this.paddingRight = horizontal;
    }

    public Padding(int paddingTop, int paddingLeft, int paddingRight, int paddingBottom) {
        this.paddingTop = paddingTop;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }
}
