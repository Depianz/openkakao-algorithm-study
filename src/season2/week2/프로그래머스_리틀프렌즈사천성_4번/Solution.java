package season2.week2.프로그래머스_리틀프렌즈사천성_4번;
import java.util.*;
class Solution {
    public String solution(int m, int n, String[] board) {
        char[][] arr = new char[m][n];
        for(int i = 0; i< m; i++){
            arr[i] = board[i].toCharArray();
        }
        ArrayList<Character> al = new ArrayList<Character>();
        for(int i =0 ; i< m ; i++){
            for(int j =0; j< n; j++){
                if(arr[i][j]!='.'&& arr[i][j]!='*' && !al.contains(arr[i][j]))al.add(arr[i][j]);
            }
        }
        Collections.sort(al);
        StringBuilder ans = new StringBuilder();
        for(int a = 0; a<al.size();a++){
            char C = al.get(a);
            loop:
            for(int I = 0; I< m; I++){
                for(int J = 0; J<n; J++){
                    if(arr[I][J]==C){//만약 같으면 bfs로 한번 꺽으면서, 가능한거 찾기
                        //상하좌우,1,2,3,4//사실 근데 상은 필요없다.
                        Queue<int[]> q = new LinkedList<int[]>();
                        q.add(new int[]{I,J,2,0});//new int[4]; //i, j, d, cnt (위치, 방향, 꺾은 수 )
                        q.add(new int[]{I,J,3,0});//new int[4]; //i, j, d, cnt (위치, 방향, 꺾은 수 )
                        q.add(new int[]{I,J,4,0});//new int[4]; //i, j, d, cnt (위치, 방향, 꺾은 수 )
                        while(!q.isEmpty()){// q에 암것도 없지 않을 때까지
                            int[] t = q.poll();
                            int i = t[0];int j = t[1]; int d = t[2];
                            //하
                            if(i<m-1 && !(i+1==I && j==J) && d==2){ //우선 만약 같은 방향이면,
                                if(arr[i+1][j]==C){ //같은 값이면?
                                    arr[I][J] = '.';arr[i+1][j]='.'; //그 위치 비워주고,
                                    ans.append(C);//ans에 값 붙여주고,
                                    al.remove(a); a=-1;//al지우고, 첨부터 시작
                                    break loop;
                                }else if(arr[i+1][j]=='.'){// 그렇지 않고 만약 지나갈 수 있으면,
                                    q.add(new int[]{i+1,j,2,t[3]}); //q에 넣기
                                }
                            }else if(i<m-1 && !(i+1==I && j==J) && t[3]==0 ){ // 다른방향이고,꺾을 수 있다면,
                                if(arr[i+1][j]==C){ //같은 값이면?
                                    arr[I][J] = '.';arr[i+1][j]='.'; //그 위치 비워주고,
                                    ans.append(C);//ans에 값 붙여주고,
                                    al.remove(a); a=-1;//al지우고, 첨부터 시작
                                    break loop;
                                }else if(arr[i+1][j]=='.'){// 그렇지 않고 만약 지나갈 수 있으면,
                                    q.add(new int[]{i+1,j,2,1}); //q에 넣기
                                }
                            }
                            //좌
                            if(j>0 && !(i==I && j-1==J) && d==3){ //우선 만약 같은 방향이면,
                                if(arr[i][j-1]==C){ //같은 값이면?
                                    arr[I][J] = '.';arr[i][j-1]='.'; //그 위치 비워주고,
                                    ans.append(C);//ans에 값 붙여주고,
                                    al.remove(a); a=-1;//al지우고, 첨부터 시작
                                    break loop;
                                }else if(arr[i][j-1]=='.'){// 그렇지 않고 만약 지나갈 수 있으면,
                                    q.add(new int[]{i,j-1,3,t[3]}); //q에 넣기
                                }
                            }else if(j>0 && !(i==I && j-1==J) &&t[3]==0 ){ // 다른방향이고,꺾을 수 있다면,
                                if(arr[i][j-1]==C){ //같은 값이면?
                                    arr[I][J] = '.';arr[i][j-1]='.'; //그 위치 비워주고,
                                    ans.append(C);//ans에 값 붙여주고,
                                    al.remove(a); a=-1;//al지우고, 첨부터 시작
                                    break loop;
                                }else if(arr[i][j-1]=='.'){// 그렇지 않고 만약 지나갈 수 있으면,
                                    q.add(new int[]{i,j-1,3,1}); //q에 넣기
                                }
                            }//우
                            if(j<n-1&& !(i==I && j+1==J) && d==4){ //우선 만약 같은 방향이면,
                                if(arr[i][j+1]==C){ //같은 값이면?
                                    arr[I][J] = '.';arr[i][j+1]='.'; //그 위치 비워주고,
                                    ans.append(C);//ans에 값 붙여주고,
                                    al.remove(a); a=-1;//al지우고, 첨부터 시작
                                    break loop;
                                }else if(arr[i][j+1]=='.'){// 그렇지 않고 만약 지나갈 수 있으면,
                                    q.add(new int[]{i,j+1,4,t[3]}); //q에 넣기
                                }
                            }else if(j<n-1&& !(i==I && j+1==J) && t[3]==0 ){ // 다른방향이고,꺾을 수 있다면,
                                if(arr[i][j+1]==C){ //같은 값이면?
                                    arr[I][J] = '.';arr[i][j+1]='.'; //그 위치 비워주고,
                                    ans.append(C);//ans에 값 붙여주고,
                                    al.remove(a); a=-1;//al지우고, 첨부터 시작
                                    break loop;
                                }else if(arr[i][j+1]=='.'){// 그렇지 않고 만약 지나갈 수 있으면,
                                    q.add(new int[]{i,j+1,4,1}); //q에 넣기
                                }
                            }
                        }

                    }
                }
            }
        }
        return al.size()!=0?"IMPOSSIBLE":ans.toString();
    }
}