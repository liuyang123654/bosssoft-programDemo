/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: Subject.java
 * Author: LiuYang
 * Date: 2024/5/15 16:23
 * Description:
 * 主题类
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.basic.ability;

import com.bosssoft.exception.ServiceException;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @description: 观察者接口，FileTransferProgressObserver.java实现了这个接口
 * @author: LiuYang
 * @date: 2024/05/16 20:44
 * @return: null
 **/
public interface IObserver {
    /**
     * @param subject
     * @param writer
     * @throws IOException
     * @throws ServiceException
     */
    public void update(ISubject subject, BufferedWriter writer) throws IOException, ServiceException;
}