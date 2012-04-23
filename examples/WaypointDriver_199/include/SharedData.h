

#ifndef SHARED_DATA_H
#define SHARED_DATA_H

#include "Transport/OS.h"

class SharedData
{
public:
	static SharedData* get()
	{
		static SharedData* singleton = NULL;
		if (!singleton) singleton = new SharedData();
		return singleton;
	};

	double getX()
	{
		mutex.lock();
		double temp = x;
		mutex.unlock();
		return temp;
	};

	double getY()
	{
		mutex.lock();
		double temp = y;
		mutex.unlock();
		return temp;
	};

	double getYaw()
	{
		mutex.lock();
		double temp = yaw;
		mutex.unlock();
		return temp;
	};

	void setX(double val)
	{
		mutex.lock();
		x = val;
		mutex.unlock();
	};

	void setY(double val)
	{
		mutex.lock();
		y = val;
		mutex.unlock();
	};

	void setYaw(double val)
	{
		mutex.lock();
		yaw = val;
		mutex.unlock();
	};
	
private:
	SharedData()
	{
		x = y= yaw = 0;
	};

	double x, y, yaw;
	DeVivo::Junior::JrMutex mutex;
};

#endif
