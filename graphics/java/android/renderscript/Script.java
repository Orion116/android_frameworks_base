/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.renderscript;

/**
 * @hide
 **/
public class Script extends BaseObj {
    boolean mIsRoot;

    Script(int id, RenderScript rs) {
        super(rs);
        mID = id;
    }

    public void destroy() {
        mRS.nScriptDestroy(mID);
        mID = 0;
    }

    public void bindAllocation(Allocation va, int slot) {
        mRS.nScriptBindAllocation(mID, va.mID, slot);
    }

    public void setClearColor(float r, float g, float b, float a) {
        //mRS.nScriptCSetClearColor(r, g, b, a);
    }

    public void setClearDepth(float d) {
        //mRS.nScriptCSetClearDepth(d);
    }

    public void setClearStencil(int stencil) {
        //mRS.nScriptCSetClearStencil(stencil);
    }


    public static class Builder {
        RenderScript mRS;
        boolean mIsRoot = false;
        byte[] mTimeZone;

        Builder(RenderScript rs) {
            mRS = rs;
        }

        public void addType(Type t) {
            mRS.nScriptCAddType(t.mID);
        }

        void transferCreate() {
            if(mTimeZone != null) {
                mRS.nScriptCSetTimeZone(mTimeZone);
            }
            mRS.nScriptCSetRoot(mIsRoot);
        }

        void transferObject(Script s) {
            s.mIsRoot = mIsRoot;
        }

        public void setTimeZone(String timeZone) {
            try {
                mTimeZone = timeZone.getBytes("UTF-8");
            } catch (java.io.UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        public void setRoot(boolean r) {
            mIsRoot = r;
        }

    }

}

