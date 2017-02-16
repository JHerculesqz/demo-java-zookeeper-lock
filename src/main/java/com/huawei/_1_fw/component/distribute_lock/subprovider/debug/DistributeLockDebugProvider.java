package com.huawei._1_fw.component.distribute_lock.subprovider.debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributeLockDebugProvider {
	// #region Fields

	private CountDownLatch startSignal = new CountDownLatch(1);// ��ʼ����
	private CountDownLatch doneSignal = null;// ��������
	private CopyOnWriteArrayList<Long> list = new CopyOnWriteArrayList<Long>();
	private AtomicInteger err = new AtomicInteger();// ԭ�ӵ���
	private ConcurrentTask[] task = null;

	// #endregion

	// #region init

	public void init(ConcurrentTask... lstTask) {
		this.task = lstTask;
		if (lstTask == null) {
			System.out.println("task can not null");
		}
		doneSignal = new CountDownLatch(lstTask.length);
		start();
	}

	// #endregion

	// #region private._start

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	private void start() {
		// �����̣߳����������̵߳ȴ��ڷ��Ŵ�
		createThread();
		// �򿪷���
		startSignal.countDown();// �ݼ��������ļ�����������������㣬���ͷ����еȴ����߳�
		try {
			doneSignal.await();// �ȴ������̶߳�ִ�����
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ����ִ��ʱ��
		getExeTime();
	}

	// #endregion

	// #region private._createThread

	/**
	 * ��ʼ�������̣߳����ڷ��Ŵ��ȴ�
	 */
	private void createThread() {
		long len = doneSignal.getCount();
		for (int i = 0; i < len; i++) {
			final int j = i;
			new Thread(new Runnable() {
				public void run() {
					try {
						startSignal.await();// ʹ��ǰ�߳�������������������֮ǰһֱ�ȴ�
						long start = System.currentTimeMillis();
						task[j].run();
						long end = (System.currentTimeMillis() - start);
						list.add(end);
					} catch (Exception e) {
						err.getAndIncrement();// �൱��err++
					}
					doneSignal.countDown();
				}
			}).start();
		}
	}

	// #endregion

	// #region private._getExeTime

	/**
	 * ����ƽ����Ӧʱ��
	 */
	private void getExeTime() {
		int size = list.size();
		List<Long> _list = new ArrayList<Long>(size);
		_list.addAll(list);
		Collections.sort(_list);
		long min = _list.get(0);
		long max = _list.get(size - 1);
		long sum = 0L;
		for (Long t : _list) {
			sum += t;
		}
		long avg = sum / size;
		System.out.println("min: " + min + ",max: " + max + ",avg: " + avg + ",err: " + err.get());
	}

	// #endregion

	public interface ConcurrentTask {
		void run();
	}
}
