package vn.anhnguyen.ticketmovie.config;


public class AppConfig {
    public static final String NORMAL_FONT = "fonts/SanFranciscoDisplay-Regular.otf";
    public static final String BOLD_FONT = "fonts/SanFranciscoDisplay-Bold.otf";
    public static final String MEDIUM_FONT = "fonts/SanFranciscoDisplay-Medium.otf";

    public static final int OS_ANDROID = 1;
    public static final String NOTIFY_COUNT = "NOTIFY_COUNT";
    public static final int TIME_DELAY_REQUEST = 120;
    public static final String FLAG_FROM_BACKGROUND = "from-background";

    // TODO: ROM
    public static final int GET_LIST_ROOM = 0;
    public static final int JOIN_ROOM = 1;
    public static final int LEAVE_ROOM = 2;
    public static final int CREATE_ROOM = 3;
    public static final int ROOM_ADD = 4;
    public static final int ROOM_REMOVE = 5;
    public static final int USER_JOIN_ROOM = 6;
    public static final int USER_LEAVE_ROOM = 7;
    public static final int LIST_USER_UPDATE = 8;

    // TODO: CREATE_ROOM = 3
    public static final int TYPE_ROOM_NORMAL = 0;
    public static final int TYPE_ROOM_SYSTEM = 1;

    // TODO: JOIN_ROOM
    public static final int GAME_TABLE_STATUS_PLAYING = 1;
    public static final int GAME_TABLE_STATUS_FINISH = 2;

    // TODO: CHA
    public static final int CHAT_REQUEST = 0;
    public static final int CHAT_ROOM_NTF = 1;
    public static final int GET_LIST_CHAT = 2;
    public static final int CHAT_ROOM_DELETE_REQ = 3;
    public static final int CHAT_ROOM_DELETE_NTF = 4;

    // TODO: GAM
    public static final int READY = 0;
    public static final int START_GAME = 1;
    public static final int START_GAME_NTF = 2;
    public static final int ANSWER_QUESTION = 3;
    public static final int RESULT_ROUND = 4;
    public static final int RESULT_GAME = 5;
    public static final int END_GAME = 6;
    public static final int INVITE_FRIEND = 7;
    public static final int RECEIVE_INVITE = 8;
    public static final int SEND_QUESTION = 9;
    public static final int GAME_END = 10;


    // TODO: RESULT_GAME = 5
    public static final int STOP_PLAY = 0;
    public static final int LOSE = 1;
    public static final int GAME_ROUND_END = 2;
    public static final int GAME_MATCH_END = 3;
}
