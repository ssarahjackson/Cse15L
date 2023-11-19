# Lab Report 4
---
 
# Step 4:

For this step, I had to type `ssh cs15lfa23ra@ieng6-201.ucsd.edu`. To save time I had this typed somewhere else, so all I had to do was highlight it by triple left-clicking my mouse, pressing `<Ctrl-C>`, and then navigating to VS code and doing `<Ctrl-V> <enter>`.

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/2079412f-1a86-49f6-a917-ee9b0210838c)

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/348fb902-7781-4391-99de-56711a0bdfd4)

# Step 5:

I used `<Alt-Tab>` to quickly navigate to the GitHub site where my fork was and copy the ssh url. Then I did `git clone <Ctrl-V> <enter>`

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/365a1b7b-459e-4a58-8380-b4cb85e172c9)

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/bab9b6a5-03eb-46a0-9559-e275eba2e187)

# Step 6:

I started by typing `cd lab7 <enter> bash test.sh <enter>`

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/99ab87a4-aa47-4827-9b0c-60b963d4f245)

By changing directories I wouldn't have to start every path with `lab7/`, and then I used the bash script to run the tests, which is much quicker than me compiling everything every time.

# Step 7:

`vim ListExamples.java <enter>` opened the file in `vim` normal mode since I knew I needed to edit that file. Then I did `?input1 <Enter>` to search for the String "input1" since that's where I needed to make my edit, and I knew it was the last instance of the String in the file, so searching with `?` instead of `/` would be much more efficient. Then 
I did `:s/index1/index2 <enter>` which substituted the original String with the new one which will fix the code. Then I saved and exited the file by doing `:wq <enter>`.

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/f06c5565-bd86-4550-83b4-ad6c9de96c9b)

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/21c15006-babf-4194-b713-528ead8b79e1)

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/61d89d9e-4b4e-448d-85f0-edac2a2c44c5)

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/0c43b3b1-b762-480b-8433-ce310d461a10)

# Step 8: 

I am now going to rerun the bash script to test the files by doing `bash test.sh <enter>`

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/3d691896-e4c5-4d24-8e4b-d88dc70ef4e3)

# Step 9:

To commit, we first need to add the modified files to the staging area. However, we can combine this with the commit by doing `git commit -a -m "fixed ListExamples.java" <enter>`. Then, to push we simply do `git push <enter>`

![image](https://github.com/ssarahjackson/cse15l-lab-reports/assets/46005666/2490bab7-51d2-4944-92f3-bf411d822f3a)

### Note:

A lot of these steps would be easier by using <up> to access previous commands, but my keyboard is a 60% so it has no arrow keys.
