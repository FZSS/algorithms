"""
Given a set of elements, return the power set of it.
Example input: [1, 3, 4]
Example output: [[], [1], [3], [4], [1, 3], [1, 4], [3, 4], [1, 3, 4]]
"""
def powerset(input_set, index):
	size = len(input_set)
	if index == 0:
		return [[]]
	output = []
	prev = powerset(input_set, index - 1)
	output.extend(prev)
	new = list(prev)
	for ele in new:
		ele.append(input_set[index - 1])
	output.extend(new)
	return output

powerset([1,4], 2)