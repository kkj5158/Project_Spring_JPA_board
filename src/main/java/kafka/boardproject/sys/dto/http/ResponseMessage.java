package kafka.boardproject.sys.dto.http;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String CREATED_USER_FAIL = "회원 가입 실패";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";

    public static final String BOARD_GET = "게시글 조회 성공";

    public static final String BOARD_GET_FAIL = "게시글이 비이었습니다.";

    public static final String BOARD_GET_ID = "특정게시글 조회 성공";

    public static final String BOARD_GET_FAIL_ID = "특정게시글 조회 실패";


    public static final String BOARD_CREATE = "게시글 작성 성공";

    public static final String BOARD_CREATE_FAIL = "게시글 작성 실패";


    public static final String BOARD_DELETE = "게시글 삭제 성공";

    public static final String BOARD_DELETE_FAIL = "게시글 삭제 실패";


    public static final String BOARD_UPDATE = "게시글 업데이트 성공";

    public static final String BOARD_UPDATE_FAIL = "게시글 업데이트 실패";

    public static final String COMMENT_CREATE = "댓글 작성 성공";

    public static final String COMMENT_CREATE_FAIL = "댓글 작성 실패";

    public static final String COMMENT_UPDATE = "댓글 수정 성공";

    public static final String COMMENT_UPDATE_FAIL = "댓글 수정 실패";

    public static final String COMMENT_DELETE = "댓글 삭제 성공";

    public static final String COMMENT_DELETE_FAIL = "댓글 삭제 실패";




}