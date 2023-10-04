# Lab Report 1
---
```Unix
[user@sahara ~/lecture1]$ cd
[user@sahara ~]$ cd /home
[user@sahara ~]$ cd /home/lecture1/messages/en-us.txt
bash: cd: /home/lecture1/messages/en-us.txt: Not a directory
```

The working directory when I first ran this code was /home/lecture1. When I used this command without any arguments it defaulted to changing the working directory to the home directory. It worked properly when I gave it a valid directory, but gave an error when I tried to give it a file. This error makes sense since I'm trying to change the working directory this obviously can't be a file.

---
![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/9b0d80eb-ccdc-4ea7-be61-3a29456199b3)
![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/8075ded8-e498-4102-83ec-b255ff10f267)

I started with my working directory being /home/lecture1. When I used this command without arguments it displayed the contents of the current working directory. However, when I gave it an argument that was a valid directory it displayed that directory's contents instead. Finally, when I used a file as the argument it just displayed the path that I gave it. It makes sense that it gave me an error since once again, this is a command relating to a directory's contents and I gave it a file.

---
![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/829dd330-f7e9-42d5-9c94-40fc860b7d77)

My working directory was the home directory when I ran these commands. When I gave it a valid file as an argument it printed out the contents of the Java file. On the other hand, it gave me an error when I gave it a directory but this makes sense since it's trying to either create or print out the contents of a file. Finally, it had a strange reaction to being given zero arguments where it just let me endlessly press enter and copied any text I tried to put in. I had to use ctrl + c to break out of the infinite looping nature of this error. This occuring does make sense since this command requires an argument and it was given none. 
