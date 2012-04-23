#define DEFAULT_AUTH 10
chan RequestControl = [10] of {pid , byte };
	mtype {CONTROL_ACCEPTED, NOT_AVAILABLE2, INSUFFICIENT_AUTHORITY};
	chan ConfirmControl = [10] of {pid , mtype };

#define isDefaultAuthorityGreater(incoming_auth) \
	 (DEFAULT_AUTH > incoming_auth)


active proctype client_app()
{
	bool result = false;
	int incoming_auth = 9;
	pid incoming_pid;
	

Receiving_Ready_NotControlled_Available:
	do
		/* transition Receive, type=RequestControl, value=msg */
	:: RequestControl ? incoming_pid, incoming_auth ->
		/* guard condition=isDefaultAuthorityGreater(msg) - this looks impossible to automate*/
		
		if
		:: ! isDefaultAuthorityGreater(incoming_auth) -> ConfirmControl ! _pid, CONTROL_ACCEPTED;
		fi
		unless {
		if
		::  isDefaultAuthorityGreater(incoming_auth) ->
			/* action Send ('ConfirmControl', 'INSUFFICIENT_AUTHORITY', transportData)  */
			ConfirmControl ! _pid, INSUFFICIENT_AUTHORITY;
		fi;}
	od;
		

}

active proctype test()
{
	pid tmppid;
	mtype param;
	int x=0;

	do
	:: x++;
	:: x > 100 -> break;	
	:: ConfirmControl ? tmppid, param ->
		printf("ConfirmControl : %e", param);
	:: RequestControl ! _pid, 9
	:: RequestControl ! _pid, 10
	:: RequestControl ! _pid, 15
	:: RequestControl ! _pid, 20
	od;
}
