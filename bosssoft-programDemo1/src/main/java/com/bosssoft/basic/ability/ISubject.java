/**
 * Copyright (C), 2001-2031, www.bosssoft.com.cn
 * FileName: ISubject.java
 * Author: LiuYang
 * Date: 2024/5/16 8:52
 * Description:
 * 观察的主题接口
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.basic.ability;

import com.bosssoft.exception.ServiceException;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @className: ISubject
 * @description: 观察的主题接口，FileTransferTask.java类实现了这个接口
 * @author: LiuYang
 * @date: 2024/5/16 8:52
 * @since 1.0
 **/
public interface ISubject {

    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(IObserver observer);

    /**
     * 移除观察者
     *
     * @param observer
     */
    void removeObserver(IObserver observer);

    /**
     * 通知观察者
     *
     * @param bufferedWriter
     * @throws IOException
     * @throws ServiceException
     */
    void notifyObservers(BufferedWriter bufferedWriter) throws IOException, ServiceException;
}