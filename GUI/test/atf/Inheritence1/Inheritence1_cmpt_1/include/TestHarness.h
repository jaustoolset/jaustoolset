#ifndef __TEST_HARNESS_H
#define __TEST_HARNESS_H

const unsigned int CAction_Top1_To_Top2Action     = 1;
const unsigned int CDefaultTransitionAction       = 2;
const unsigned int CTop1_Intermediary1_EntryAction= 4;
const unsigned int CTop1_Intermediary1_ExitAction = 8;
const unsigned int CTop2_EntryAction              = 16;
const unsigned int CTop2_ExitAction               = 32;
const unsigned int CTop2_Intermediary1_EntryAction= 64;
const unsigned int CTop2_Intermediary1_ExitAction = 128;
const unsigned int CTop2_Intermediary2_EntryAction= 256;
const unsigned int CTop2_Intermediary2_ExitAction = 512;
const unsigned int CTop2Intermediary1_toTop1Intermediary1Action = 1024;


#endif