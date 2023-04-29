# reads a number of seconds (an integer) and then prints the same amount of time given in hours, minutes and seconds. th = Hours, 
# tm = minutes, ts = seconds.
t = int(input("Input a time in seconds "))
th = t//3600
tm = (t%3600)//60
ts = t%60
print ("This corresponds to:" ,th, "hours" ,tm, "minutes and" ,ts, "seconds")