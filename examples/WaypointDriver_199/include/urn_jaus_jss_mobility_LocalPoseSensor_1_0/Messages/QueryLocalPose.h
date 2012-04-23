#ifndef URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_QUERYLOCALPOSE_H
#define URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_QUERYLOCALPOSE_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{

class DllExport QueryLocalPose: public JTS::Message
{
public:
	static const int ID = 0x2403;
	
	class DllExport AppHeader
	{
	public:
		class DllExport HeaderRec
		{
		public:
			void setParent(AppHeader* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getMessageID();
			int setMessageID(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			HeaderRec &operator=(const HeaderRec &value);
			bool operator==(const HeaderRec &value) const;
			bool operator!=(const HeaderRec &value) const;
			HeaderRec();
			HeaderRec(const HeaderRec &value);
			virtual ~HeaderRec();
		
		protected:
			AppHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		AppHeader &operator=(const AppHeader &value);
		bool operator==(const AppHeader &value) const;
		bool operator!=(const AppHeader &value) const;
		AppHeader();
		AppHeader(const AppHeader &value);
		virtual ~AppHeader();
	
	protected:
		HeaderRec m_HeaderRec;
	};
	class DllExport Body
	{
	public:
		class DllExport QueryLocalPoseRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getPresenceVector();
			int setPresenceVector(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryLocalPoseRec &operator=(const QueryLocalPoseRec &value);
			bool operator==(const QueryLocalPoseRec &value) const;
			bool operator!=(const QueryLocalPoseRec &value) const;
			QueryLocalPoseRec();
			QueryLocalPoseRec(const QueryLocalPoseRec &value);
			virtual ~QueryLocalPoseRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_PresenceVector;
		};
	
		QueryLocalPoseRec* const getQueryLocalPoseRec();
		int setQueryLocalPoseRec(const QueryLocalPoseRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		Body &operator=(const Body &value);
		bool operator==(const Body &value) const;
		bool operator!=(const Body &value) const;
		Body();
		Body(const Body &value);
		virtual ~Body();
	
	protected:
		QueryLocalPoseRec m_QueryLocalPoseRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryLocalPose &operator=(const QueryLocalPose &value);
	bool operator==(const QueryLocalPose &value) const;
	bool operator!=(const QueryLocalPose &value) const;
	QueryLocalPose();
	QueryLocalPose(const QueryLocalPose &value);
	virtual ~QueryLocalPose();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_QUERYLOCALPOSE_H
