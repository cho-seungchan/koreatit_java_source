/*##### 모듈화 #####*/
let replyService = (function(){

    function getList(callback){
        $.ajax({
            url: `/replies/list/${boardNo}/${replyPage}/${rowCount}?type=${replyType}&keyword=${replyKeyword}`,
            dataType: "json",
            success: function(replyDTO){
                if(callback){
                    callback(replyDTO);
                }
            }
        });
    }

    function write(callback){
        $.ajax({
            url: "/replies/write",
            type: "post",
            data: JSON.stringify({replyWriter: $("input[name='replyWriter']").val(), replyContent: $("textarea[name='replyContent']").val(), boardNo: boardNo}),
            contentType: "application/json;charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    function modify(callback){
        $.ajax({
            url: "/replies/modify",
            type: "post",
            data: JSON.stringify({replyContent: $("textarea.reply-content-modify").val(), replyId: $("textarea.reply-content-modify").closest("li").data("reply-id")}),
            contentType: "application/json;charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    function remove(replyId, callback){
        $.ajax({
            url: `/replies/${replyId}`,
            type: "delete",
            success: function(){
                if(callback){
                    callback(replyId);
                }
            }
        });
    }

    return {getList: getList, write: write, modify: modify, remove: remove};
})();

replyService.getList(showList);

/*##### EVNET #####*/
let check = false;


/*##### 검색 #####*/
$("input[name=keyword]").on("keydown", function(e){
    if(e.keyCode == 13){
        e.preventDefault();
        replyPage = 1;
        $("a.search").trigger("click");
    }
})

$("a.search").on("click", function(){
    replyType = $("select[name=type]").val();
    replyKeyword = $("input[name='keyword']").val();

    if(!replyType || !replyKeyword) {
        return;
    }
    $("ul.replies").html("");
    replyPage = 1;
    replyService.getList(showList);
});

/*###############*/

$("a.more-replies").on("click", function(){
    replyPage++;
    replyService.getList(showList);
});

$("ul.replies").on("click", "a.remove", function(){
    let i = $("a.remove").index(this);
    let replyId = $(this).closest("li").data("reply-id");
    replyService.remove(replyId, deleteReply);
});
let count = 0;
function deleteReply(replyId){
    $("li").remove(`li[data-reply-id=${replyId}]`);
    count++;
    if(count == 10 * replyPage){location.reload();}
}

$("ul.replies").on("click", "a.modify", function(){
    check = false;
    let i = $(this).parent("div").attr("id");
    replyService.modify(update);
});

function update(){
    const $p = $("textarea.reply-content-modify").closest("div").find("p.reply-content");
    $p.text($("textarea.reply-content-modify").val());
    $("a").remove("a.modify");
    $("a").remove("a.modify-cancel");
    $("textarea").remove("textarea.reply-content-modify");
    $p.show();
    $p.closest("li").find("a.modify-ready").show();
    $p.closest("li").find("a.remove").show();
}

$("ul.replies").on("click", "a.modify-ready", function(){
    if(check){return;}
    let i = $("a.modify-ready").index(this);
    $(this).hide();
    $("a.remove").eq(i).hide();
    $(this).parent("div").append(`<a class="modify" style="cursor: pointer; margin-right:5px">수정완료</a>`);
    $(this).parent("div").append(`<a class="modify-cancel" style="cursor: pointer"">취소</a>`);
    $("p.reply-content").eq(i).hide();
    $("p.reply-content").eq(i).closest("div").append(`<textarea name="replyContent" style="resize: none; width: 100%" cols="120" class="reply-content-modify">${$("p.reply-content").eq(i).text()}</textarea>`)
    check = true;
});

$("ul.replies").on("click", "a.modify-cancel", function(){
    check = false;
    let i = $(this).closest("div").attr("id");
    $("a").remove("a.modify");
    $("a").remove("a.modify-cancel");
    $.remove("textarea.reply-content-modify");
    $("p.reply-content").eq(i).show();
    $("a.modify-ready").eq(i).show();
    $("a.remove").eq(i).show();
});

$("a.register").on("click", function(){
    $("div.register-form").show();
    $(this).hide();
});

$("a.cancel").on("click", function(){
    $("div.register-form").hide();
    $("a.register").show();
    $("input[name='replyWriter']").val(""); /*선택된 요소의 값을 가져오거나 설정. 요소의 값을 초기화*/
    $("textarea[name='replyContent']").val("");
});
/*##### DOM #####*/
$("a.finish").on("click", function(){
    replyService.write(register);
});

function register(){
    replyPage = 1;
    $("ul.replies").html(""); /* 선택된 요소의 html을 가져오거나 설정. ul.replies 요소 내부의 모든 HTML 내용을 지우는 코드입니다. 즉, <ul class="replies"></ul>의 상태로 초기화 */
    $("a.cancel").trigger("click"); /* 지정된 이벤트를 해당 요소에서 강제로 발생시킵니다. 취소버튼이 눌린거로 처리 */
    replyService.getList(showList);
}

function showList(replyDTO){
    let replies = replyDTO.replies;
    let countOfNextPage = replyDTO.countOfNextPage;
    let text = ""
    if(replies.length == 0 && replyPage == 1) {
        text = `
            <p>댓글이 없습니다.</p>
        `
        $("a.more-replies").hide();
    }
    if(countOfNextPage == 0){
        $("a.more-replies").hide();
    }
    else{
        $("a.more-replies").show();
    }
    replies.forEach((reply, i) => {
        text += `
            <li style="display: block" data-reply-id="${reply.replyId}">
                <div style="display: flex; justify-content: space-between">
                    <strong style="display: block">${reply.replyWriter}</strong>
                    <div id="${i}">
                        <a class="modify-ready" style="cursor: pointer; margin-right:5px">수정</a>
                        <a class="remove" style="cursor: pointer">삭제</a>
                    </div>
                </div>
                <div style="display: flex; justify-content: space-between;">
                    <div>
                        <p class="reply-content">${reply.replyContent}</p>
                    </div>
                    <p style="text-align: right">
                        <strong class="date">
                            ${elapsedTime(reply.replyRegisterDate)}
                        </strong>
                    </p>
                </div>
                <div class="line"></div>
            </li>
        `;
    });
    $("ul.replies").append(text);
}

function elapsedTime(date) {
    const start = new Date(date);
    const end = new Date();

    const gap = (end - start) / 1000;

    const times = [
        {name: "년", time: 60 * 60 * 24 * 365},
        {name: "개월", time: 60 * 60 * 24 * 30},
        {name: "일", time: 60 * 60 * 24},
        {name: "시간", time: 60 * 60},
        {name: "분", time: 60},
    ]

    for(const time of times){
        const gapTime = Math.floor(gap / time.time);
        if(gapTime > 0){
            return `${gapTime}${time.name} 전`
        }
    }

    return "방금 전";
}

