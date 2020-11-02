package com.config.server.utils;
/**
 * ���������ɷֲ�ʽΨһid
 * ��˾:˼����Ϣ
 * ����:��Ⱥ
 * �汾:3.0.0
 * ��������:2020��6��9������10:04:56
 */
public class SnowFlakeUtil {
	
	private final long id;
	/**
	 * ʱ����ʼ��ǵ㣬��Ϊ��׼��һ��ȡϵͳ�����ʱ��
	 */
	private final long epoch = 1524291141010L;
	/**
	 * ������ʶλ��
	 */
	private final long workerIdBits = 10L;
	/**
	 * ����ID���ֵ: 1023
	 */
	private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
	/**
	 * 0����������
	 */
	private long sequence = 0L;
	/**
	 * ����������λ
	 */
	private final long sequenceBits = 12L;

	/**
	 * 12
	 */
	private final long workerIdShift = this.sequenceBits;
	/**
	 * 22
	 */
	private final long timestampLeftShift = this.sequenceBits
			+ this.workerIdBits;
	/**
	 * 4095,111111111111,12λ
	 */
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits;
	private long lastTimestamp = -1L;

	private SnowFlakeUtil(long id) {
		if (id > this.maxWorkerId || id < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					this.maxWorkerId));
		}
		this.id = id;
	}

	public synchronized long nextId() {
		long timestamp = timeGen();
		if (this.lastTimestamp == timestamp) {
			// �����һ��timestamp���²�������ȣ���sequence��һ(0-4095ѭ��);
			// ���µ�timestamp��sequence��0��ʼ
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				// ��������timestamp
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
//			log.error(String
//					.format("clock moved backwards.Refusing to generate id for %d milliseconds",
//							(this.lastTimestamp - timestamp)));
			return -1;
		}

		this.lastTimestamp = timestamp;
		return timestamp - this.epoch << this.timestampLeftShift
				| this.id << this.workerIdShift | this.sequence;
	}

	private static SnowFlakeUtil flowIdWorker = new SnowFlakeUtil(1);

	public static SnowFlakeUtil getFlowIdInstance() {
		return flowIdWorker;
	}

	/**
	 * �ȴ���һ������ĵ���, ��֤���صĺ������ڲ���lastTimestamp֮��
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * ���ϵͳ��ǰ������
	 */
	private static long timeGen() {
		return System.currentTimeMillis();
	}
	
	public static long getSnowFlakeId(){
		return SnowFlakeUtil.getFlowIdInstance().nextId();
	}

//	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			System.out.println(SnowFlakeUtil.getSnowFlakeId());
//		}
//	}
}
