import statistics

d = [41, 64, 60, 50, 43]
i = [65, 54, 52, 57, 64]
t = [5, 5, 6, 5, 6]
bs = [20, 18, 21, 16, 14]
ba = [25, 26, 26, 30, 29]

print(f"Direct Buffer: {statistics.fmean(d)}")
print(f"Indirect Buffer: {statistics.fmean(i)}")
print(f"Transfer: {statistics.fmean(t)}")
print(f"Buffered Stream: {statistics.fmean(bs)}")
print(f"Byte Array: {statistics.fmean(ba)}")
