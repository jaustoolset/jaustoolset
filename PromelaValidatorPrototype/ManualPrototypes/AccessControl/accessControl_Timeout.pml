/* accessControl_Timeout.pml
 *
 * A model of AS5710 Access Control Timeout.  The timeout is used to force a client
 * to reacquire access control on a regular basis.  Note:  there are no time functions
 * in Promela, so the is merely a placeholder that can occasionally cause a timeout. The 
 * times are not in any way accurate or measurable.
 *
 * Author - Chuck Messmer
 * Date - January 2011
 *
 *
 * include this file in any model that uses Timeout functionality.
 *
 */


#define TIMEOUT_DURATION 10


/***********************  Timeout Events and Actions *********************************/
	chan Timeout = [10] of {pid};
	chan ResetTimerMsg = [10] of {pid};

inline ResetTimer()
{
	ResetTimerMsg ! _pid;
}


active proctype timer_proc()
{
	int timer_val = 0;
	pid tmp_pid;

start_timer:
	do
	:: ResetTimerMsg? tmp_pid ->
		timer_val = 0;
	:: timer_val >= TIMEOUT_DURATION ->
		Timeout!_pid;
		goto end_timer;
	::
		timer_val++;
	od;
end_timer:
	do
	:: ResetTimerMsg? tmp_pid ->
		goto start_timer;
	od; 
}

