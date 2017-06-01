
Things you need to know to use this app:

	this app deploy the requirements as described in cs349A3, following
is the site:
  https://www.student.cs.uwaterloo.ca/~cs349/s16/assignments/a3.html


However, there are two things to be aware: 
	first of all, the canva will not 
be clear right away after you tap and hold on the delete button. However,
the model has changed, and you will see canva is cleared when you drawing
next shape on the canva. This is because I could not figure out how to force
redraw outside of onTouchEvent method, so I replied on touch event
to happen to show the affect of clearing canva.
	secondly, how you will use undo button is that, first you need to
select undo button, then tap on the canva to undo last step. This is just 
like how you will use every other tool buttons.

Reference:
	I learned to use singleton parttern for my moel and learn how to
create canva and draw shapes on it from "Android Programming:The Big Nerd
Ranch Guide" by Philips & Hardy, particularly from chapter 7,8,9,32.

For the redo functionality to be possible, it requires me to learn how to
deep copy arrayList in java. I learn it from this site of stackoverflow:
http://stackoverflow.com/questions/4199429/proper-way-to-deep-copy-with-copy-constructor-instead-of-object-clone.

if you spot any similarity from oter code. These could be why.


