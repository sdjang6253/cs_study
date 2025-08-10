package collection.map.test;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserHistory {
    private Deque<String> stack;
    private String nowPage;
    public BrowserHistory() {
        this.stack = new ArrayDeque<>();
        this.nowPage = "";
    }

    public void visitPage(String page){
        System.out.println("방문 : " + page);
        if(nowPage.isEmpty()){
            nowPage = page;
        }else {
            stack.push(nowPage);
            nowPage = page;
        }
    }

    public String goBack(){
        nowPage = stack.pop();
        System.out.println("뒤로 가기: "  + nowPage);
        return nowPage;
    }
}
