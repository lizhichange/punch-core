/*
 * Copyright (c) 2017. Dawn Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fulihui.punch.except;



/**
 * @author Willard.Hu on 2017/9/29.
 */
public class CustomerException extends RuntimeException {
    private static final long serialVersionUID = -3201168045496121202L;

    /** 异常代码 */
    private Object errcode;
    /** 异常信息 */
    private String errmsg;

    public CustomerException(Object errcode, String errmsg) {
        super(errmsg);
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public CustomerException(IError<?> error) {
        super(error.errmsg());
        this.errcode = error.errcode();
        this.errmsg = error.errmsg();
    }

    public Object getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
