package com.example.andriod.tablayoutproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author jiangqq
 * @date 2018/5/2
 * Email:3350730342@qq.com
 */
public class TabView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = TabView.class.getSimpleName();
    /**
     * 图片和文字的默认间距
     */
    private static final int DRAWABLE_DEFAULT_PADDING = 0;
    /**
     * 图片的最大占比
     */
    private static final float DRAWABLE_DEFAULT_RATE = 0.8F;
    /**
     * 图片默认的位置
     */
    private static final String DRAWABLE_DEFAULT_DIRECTION = DIRECTION.TOP;
    /**
     * 图片的默认宽度
     */
    private static final int DRAWABLE_DEFAULT_WIDTH = 0;
    /**
     * 图片的默认高度
     */
    private static final int DRAWABLE_DEFAULT_HEIGHT = 0;
    /**
     * 设置的图片资源
     */
    private Drawable mDrawable;
    /**
     * 图片的宽度
     */
    private int mDrawableWidth;
    /**
     * 图片的高度
     */
    private int mDrawableHeight;
    /**
     * 图片和文字的间距
     */
    private int mDrawablePadding = DRAWABLE_DEFAULT_PADDING;

    /**
     * 图片位置
     */
    private String mDrawableDirection = DRAWABLE_DEFAULT_DIRECTION;

    /**
     * 图片位置
     */
    @StringDef({DIRECTION.LEFT, DIRECTION.RIGHT, DIRECTION.TOP, DIRECTION.BOTTOM})
    @interface DIRECTION {
        String LEFT = "left";
        String RIGHT = "right";
        String TOP = "top";
        String BOTTOM = "bottom";
    }

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context, attrs);
        drawDrawable();
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);

        mDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.TabView_drawableWidth, DRAWABLE_DEFAULT_WIDTH);
        mDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.TabView_drawableHeight, DRAWABLE_DEFAULT_HEIGHT);
        mDrawableDirection = typedArray.getString(R.styleable.TabView_drawableDirection);
        mDrawable = typedArray.getDrawable(R.styleable.TabView_drawableSrc);
        mDrawablePadding =
            typedArray.getDimensionPixelSize(R.styleable.TabView_drawablePadding, DRAWABLE_DEFAULT_PADDING);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mDrawableWidth > MeasureSpec.getSize(widthMeasureSpec) * DRAWABLE_DEFAULT_RATE) {
            mDrawableWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * DRAWABLE_DEFAULT_RATE);
        }
        if (mDrawableHeight > MeasureSpec.getSize(heightMeasureSpec) * DRAWABLE_DEFAULT_RATE) {
            mDrawableHeight = (int) (MeasureSpec.getSize(heightMeasureSpec) * DRAWABLE_DEFAULT_RATE);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void drawDrawable() {
        if (mDrawable != null) {
            Bitmap bitmap = ((BitmapDrawable) mDrawable).getBitmap();
            Drawable drawable;
            if (mDrawableWidth != 0 && mDrawableHeight != 0) {
                drawable = new BitmapDrawable(getResources(), getBitmap(bitmap, mDrawableWidth, mDrawableHeight));
            } else {
                drawable = new BitmapDrawable(getResources(),
                    Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true));
            }
            // 必须设置，否则无法显示
            drawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
            // 设置padding
            this.setCompoundDrawablePadding(mDrawablePadding);
            switch (mDrawableDirection) {
                case DIRECTION.LEFT: {
                    this.setCompoundDrawables(drawable, null, null, null);
                    break;
                }
                case DIRECTION.RIGHT: {
                    this.setCompoundDrawables(null, null, drawable, null);
                    break;
                }
                case DIRECTION.TOP: {
                    this.setCompoundDrawables(null, drawable, null, null);
                    break;
                }
                case DIRECTION.BOTTOM: {
                    this.setCompoundDrawables(null, null, null, drawable);
                    break;
                }
                default:
                    break;
            }
        }
    }

    public Bitmap getBitmap(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = (float) newWidth / width;
        float scaleHeight = (float) newHeight / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }
}
