/* accessControl_Static.pml
 *
 * A set of AccessControl inline functions that represent "interpretation" attributes for certain actions.
 * Author - Chuck Messmer
 * Date - January 2011
 *
 *
 * include this file in any model that needs the functionality.
 *
 */


/* some stuff that is implementation specific and can't be automated */

/* to implement as a guard, call like this  :: isDefaultAuthorityGreater(input_auth, result); result ->  */
/* if a logical operator is required the apply it to the last part:  !result ->    */ 
#define DEFAULT_AUTH 0

/*  implementation of action calls that allow for the action definition to change without regenerating code. 
*    It's also a lot more readable  
*/
#define isDefaultAuthorityGreater(auth) (DEFAULT_AUTH > auth)

#define isCurrentAuthorityLess(auth) (current_client_auth < auth)

#define StoreAddress(input_pid) current_client_pid = input_pid;

#define SetAuthorityAction(auth) current_client_auth = auth;

#define isControllingClient(input_pid) (current_client_pid == input_pid)

#define isAuthorityValid(input_pid, input_auth) (input_pid == current_client_pid && input_auth <= current_client_auth && input_auth >= DEFAULT_AUTH)
