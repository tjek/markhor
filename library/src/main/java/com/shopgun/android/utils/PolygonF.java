package com.shopgun.android.utils;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.Locale;

/**
 *
 * Used by the Roi classes to return float coordinate arrays and to
 * determine if a point is inside or outside of spline fitted selections.
 *
 * https://imagej.nih.gov/ij/source/ij/process/FloatPolygon.java
 * https://imagej.nih.gov/ij/disclaimer.html
 *
 * ImageJ is being developed at the National Institutes of Health by an employee of the
 * Federal Government in the course of his official duties.
 * Pursuant to Title 17, Section 105 of the United States Code, this software is not subject to
 * copyright protection and is in the public domain. ImageJ is an experimental system.
 * NIH assumes no responsibility whatsoever for its use by other parties, and makes no
 * guarantees, expressed or implied, about its quality, reliability, or any other characteristic.
 *
 */
public class PolygonF implements Parcelable {

    private RectF mBounds;
    private float mMinX, mMinY, mMaxX, mMaxY;

    /** The number of points. */
    public int npoints;

    /* The array of x coordinates. */
    public float xpoints[];

    /* The array of y coordinates. */
    public float ypoints[];

    /** Constructs an empty PolygonF. */
    public PolygonF() {
        this(10);
    }

    public PolygonF(int size) {
        this(new float[size], new float[size], 0);
    }

    /**
     * Constructs a {@link PolygonF} from x and y arrays.
     * @param xpoints an array of x-coordinates for the {@link PolygonF}
     * @param ypoints an array of x-coordinates for the {@link PolygonF}
     */
    public PolygonF(float xpoints[], float ypoints[]) {
        this(xpoints, ypoints, xpoints.length);
    }

    /**
     * Constructs a {@link PolygonF} from x and y arrays.
     * @param xpoints an array of x-coordinates for the {@link PolygonF}
     * @param ypoints an array of x-coordinates for the {@link PolygonF}
     * @param npoints the number of points in the {@link PolygonF}
     */
    public PolygonF(float xpoints[], float ypoints[], int npoints) {
        if (xpoints.length!=ypoints.length)
            throw new IllegalArgumentException("xpoints.length!=ypoints.length");
        this.npoints = npoints;
        this.xpoints = xpoints;
        this.ypoints = ypoints;
    }

    /**
     * Constructs a {@link PolygonF} from an existing {@link PolygonF}.
     * @param polygon A polygon
     */
	public PolygonF(PolygonF polygon) {
        this(Arrays.copyOf(polygon.xpoints, polygon.xpoints.length),
                Arrays.copyOf(polygon.ypoints, polygon.ypoints.length),
                polygon.npoints);
	}

    /**
     * Check if a given point (x, y) is inside this {@link PolygonF}
     *
     * <p>This is a Java version of the remarkably small C program by W. Randolph Franklin at
     * http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html#The%20C%20Code</p>
     *
     * @param x The x-coordinate of the point to check
     * @param y The y-coordinate of the point to check
     * @return {@code true} if the point (x, y) is inside this {@link PolygonF}, else {@code false}.
     */
    public boolean contains(float x, float y) {
        boolean inside = false;
        for (int i=0, j=npoints-1; i<npoints; j=i++) {
            if (((ypoints[i]>y)!=(ypoints[j]>y)) &&
                    (x<(xpoints[j]-xpoints[i])*(y-ypoints[i])/(ypoints[j]-ypoints[i])+xpoints[i]))
                inside = !inside;
        }
        return inside;
    }

    /**
     * @return Get the bounding rect for this {@link PolygonF}
     */
    public RectF getBounds() {
        if (npoints==0)
            return new RectF();
        if (mBounds ==null)
            calculateBounds(xpoints, ypoints, npoints);
        return mBounds;
    }

    /**
     * Calculate the bounds for this {@link PolygonF}
     * @param xpoints The x-coordinates for the polygon
     * @param ypoints The y-coordinates for the polygon
     * @param npoints The number of points in the polygon
     */
    void calculateBounds(float[] xpoints, float[] ypoints, int npoints) {
        mMinX = Float.MAX_VALUE;
        mMinY = Float.MAX_VALUE;
        mMaxX = Float.MIN_VALUE;
        mMaxY = Float.MIN_VALUE;
        for (int i=0; i<npoints; i++) {
            float x = xpoints[i];
            mMinX = Math.min(mMinX, x);
            mMaxX = Math.max(mMaxX, x);
            float y = ypoints[i];
            mMinY = Math.min(mMinY, y);
            mMaxY = Math.max(mMaxY, y);
        }
        int iMinX = (int)Math.floor(mMinX);
        int iMinY = (int)Math.floor(mMinY);
        mBounds = new RectF(iMinX, iMinY, (int)(mMaxX -iMinX+0.5), (int)(mMaxY -iMinY+0.5));
    }

    /**
     * Add a point to the polygon
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public void addPoint(float x, float y) {
        if (npoints==xpoints.length) {
            float[] tmp = new float[npoints*2];
            System.arraycopy(xpoints, 0, tmp, 0, npoints);
            xpoints = tmp;
            tmp = new float[npoints*2];
            System.arraycopy(ypoints, 0, tmp, 0, npoints);
            ypoints = tmp;
        }
        xpoints[npoints] = x;
        ypoints[npoints] = y;
        npoints++;
        mBounds = null;
    }

    /**
     * Add a point to the polygon
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public void addPoint(double x, double y) {
        addPoint((float)x, (float)y);
    }

    /**
     * @return Create a deep-copy of this polygon.
     */
    public PolygonF duplicate() {
        return new PolygonF(
                Arrays.copyOf(xpoints, xpoints.length),
                Arrays.copyOf(ypoints, ypoints.length),
                npoints);
    }

    /**
     * Returns the length of this polygon or line.
     * @param isLine {@code true} if this polygon is a line, else {@code false}
     * @return the length
     */
    public double getLength(boolean isLine) {
        double dx, dy;
        double length = 0.0;
        for (int i=0; i<(npoints-1); i++) {
            dx = xpoints[i+1]-xpoints[i];
            dy = ypoints[i+1]-ypoints[i];
            length += Math.sqrt(dx*dx+dy*dy);
        }
        if (!isLine) {
            dx = xpoints[0]-xpoints[npoints-1];
            dy = ypoints[0]-ypoints[npoints-1];
            length += Math.sqrt(dx*dx+dy*dy);
        }
        return length;
    }

    @Override
    public String toString() {
        return "PolygonF(" + toShortString() + ")";
    }

    /**
     * @return a string representation of the polygon in a compact form.
     */
    public String toShortString() {
        StringBuilder sb = new StringBuilder();
        String f = "[%.2f,%.2f]";
        for (int i = 0; i < npoints; i++) {
            sb.append(String.format(Locale.US, f, xpoints[i], ypoints[i]));
        }
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mBounds, flags);
        dest.writeInt(this.npoints);
        dest.writeFloatArray(this.xpoints);
        dest.writeFloatArray(this.ypoints);
    }

    protected PolygonF(Parcel in) {
        this.mBounds = in.readParcelable(RectF.class.getClassLoader());
        this.npoints = in.readInt();
        this.xpoints = in.createFloatArray();
        this.ypoints = in.createFloatArray();
    }

    public static final Creator<PolygonF> CREATOR = new Creator<PolygonF>() {
        @Override
        public PolygonF createFromParcel(Parcel source) {
            return new PolygonF(source);
        }

        @Override
        public PolygonF[] newArray(int size) {
            return new PolygonF[size];
        }
    };
}