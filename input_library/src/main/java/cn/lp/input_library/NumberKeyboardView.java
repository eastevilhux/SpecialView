package cn.lp.input_library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Loren
 * Create_Time: 2019/9/26 14:46
 * description:
 */
public class NumberKeyboardView extends KeyboardView implements KeyboardView.OnKeyboardActionListener {
    public NumberKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private int mBackgroundColor = Color.parseColor("#cccccc");
    private Rect mDeleteDrawRect;
    private Drawable mDeleteDrawable;
    private int xmlLayoutResId;

    private EditText editText;

    // 用于区分左下角空白的按键
    private int KEYCODE_EMPTY = -10;
    private boolean isShuffleKeyValue;


    // 0-9 的数字
    private final List<Character> keyCodes = Arrays.asList(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    @SuppressLint({"CustomViewStyleable", "Recycle"})
    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberKeyboardView);
        mBackgroundColor = typedArray.getColor(R.styleable.NumberKeyboardView_keyBgroundColor, mBackgroundColor);
        mDeleteDrawable = typedArray.getDrawable(R.styleable.NumberKeyboardView_keyDeleteDrawable);
        xmlLayoutResId = typedArray.getResourceId(R.styleable.NumberKeyboardView_keyXmlLayout, R.xml.keyboard_number_password);
        KEYCODE_EMPTY = typedArray.getInt(R.styleable.NumberKeyboardView_keyEmptyValue, KEYCODE_EMPTY);
        isShuffleKeyValue = typedArray.getBoolean(R.styleable.NumberKeyboardView_isShuffleKeyValue, false);

        // 设置软键盘按键的布局
        Keyboard keyboard = new Keyboard(context,
                xmlLayoutResId);
        setKeyboard(keyboard);
        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
        if (isShuffleKeyValue) {
            shuffleKeyboard();
        }

    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 遍历所有的按键
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            // 如果是左下角空白的按键，重画按键的背景
            if (key.codes[0] == KEYCODE_EMPTY) {
                drawKeyBackground(key, canvas, mBackgroundColor);
            }
            // 如果是右下角的删除按键，重画背景，并且绘制删除的图标
            else if (key.codes[0] == Keyboard.KEYCODE_DELETE) {
                if (mDeleteDrawable == null) {
                    mDeleteDrawable = key.icon;
                }
                drawKeyBackground(key, canvas, mBackgroundColor);
                drawDeleteButton(key, canvas);
            }
        }
    }


    // 绘制删除按键
    private void drawDeleteButton(Keyboard.Key key, Canvas canvas) {
        if (mDeleteDrawable == null) {
            return;
        }

        // 计算删除图标绘制的坐标
        if (mDeleteDrawRect == null || mDeleteDrawRect.isEmpty()) {
            int intrinsicWidth = mDeleteDrawable.getIntrinsicWidth();
            int intrinsicHeight = mDeleteDrawable.getIntrinsicHeight();
            int drawWidth = intrinsicWidth;
            int drawHeight = intrinsicHeight;

            // 限制图标的大小，防止图标超出按键
            if (drawWidth > key.width) {
                drawWidth = key.width;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            }
            if (drawHeight > key.height) {
                drawHeight = key.height;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            }

            // 获取删除图标绘制的坐标
            int left = key.x + (key.width - drawWidth) / 2;
            int top = key.y + (key.height - drawHeight) / 2;
            mDeleteDrawRect = new Rect(left, top,
                    left + drawWidth, top + drawHeight);
        }

        // 绘制删除的图标
        if (mDeleteDrawRect != null && !mDeleteDrawRect.isEmpty()) {
            mDeleteDrawable.setBounds(mDeleteDrawRect.left,
                    mDeleteDrawRect.top, mDeleteDrawRect.right,
                    mDeleteDrawRect.bottom);
            mDeleteDrawable.draw(canvas);
        }
    }


    // 绘制按键的背景
    private void drawKeyBackground(Keyboard.Key key, Canvas canvas,
                                   int color) {
        ColorDrawable drawable = new ColorDrawable(color);
        drawable.setBounds(key.x, key.y,
                key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }


    private IOnKeyboardListener mOnKeyboardListener;

    public interface IOnKeyboardListener {

        void onInsertKeyEvent(String text);

        void onDeleteKeyEvent();
    }


    /**
     * 设置键盘的监听事件。
     *
     * @param listener 监听事件
     */
    public void setIOnKeyboardListener(IOnKeyboardListener listener) {
        this.mOnKeyboardListener = listener;
    }


    /**
     * @param editText 设置接收的EditText
     */
    public void setInputEditTextListener(EditText editText) {
        this.editText = editText;
        setEditText(editText);
    }


    //指定EditText与KeyboardView绑定
    private void setEditText(EditText editText) {
        //关闭进入该界面获取焦点后弹出的系统键盘
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }

    }


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        // 处理按键的点击事件
        // 点击删除按键
        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            if (editText != null) {
                int index = editText.getSelectionStart();
                Editable editable = editText.getText();
                if (!TextUtils.isEmpty(editable.toString())) {
                    if (index == 0) {
                        editable.delete(0, 0);
                    } else if (index > 0) {
                        editable.delete(index - 1, index);
                    }

                }
            }
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onDeleteKeyEvent();
            }


        }
        // 点击了非左下角按键的其他按键
        else if (primaryCode != KEYCODE_EMPTY) {
            String data = Character.toString((char) primaryCode);
            if (editText != null) {
                int index = editText.getSelectionStart();
                Editable editable = editText.getText();
                editable.insert(index, data);
            }


            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onInsertKeyEvent(
                        data);
            }
        }
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }


    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    /**
     * 随机打乱数字键盘上显示的数字顺序。
     */
    public void shuffleKeyboard() {
        Keyboard keyboard = getKeyboard();
        if (keyboard != null && keyboard.getKeys() != null
                && keyboard.getKeys().size() > 0) {
            // 随机排序数字
            Collections.shuffle(keyCodes);
            // 遍历所有的按键
            List<Keyboard.Key> keys = getKeyboard().getKeys();
            int index = 0;
            for (Keyboard.Key key : keys) {
                // 如果按键是数字
                if (key.codes[0] != KEYCODE_EMPTY
                        && key.codes[0] != Keyboard.KEYCODE_DELETE) {
                    char code = keyCodes.get(index++);
                    key.codes[0] = code;
                    key.label = Character.toString(code);
                }
            }
            // 更新键盘
            setKeyboard(keyboard);
        }
    }
}
