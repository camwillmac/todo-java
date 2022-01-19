# todo-java
Quick project I made to practise java.


I could've added the ability to remove tasks by name instead of just index but doing so would use up more cpu power.

e.g 

int i = Integer.parseInt(string_which_is_text_from_field);
tl.remove(i);

vs

String n="Text_From_Field"; //name to find
Todo ex=null;//object which will be removed if found
for (Todo t: tl){
  if (t.name.equals(n)){
    ex=t;
    break;
  }
}
if (!(ex==null)){
  tl.remove(ex);
}

*******
As you can see remove by index is a lot simpler.
