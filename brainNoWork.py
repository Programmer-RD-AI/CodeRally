from collections import defaultdict
import sys

def can_assign_problems(nk, problems_needed, problem_pool):
    assigned = set()
    assignments = defaultdict(list)

    def backtrack(category):
        if category > nk:
            return all(len(assignments[cat]) == problems_needed[cat-1] for cat in range(1, nk+1))
        
        if category not in problem_pool:
            return backtrack(category + 1)
        
        needed = problems_needed[category - 1]
        
        if not needed:
            return backtrack(category + 1)
        
        for problem in problem_pool[category]:
            if problem not in assigned:
                assigned.add(problem)
                assignments[category].append(problem)
                
                if len(assignments[category]) == needed:
                    if backtrack(category + 1):
                        return True
                elif len(assignments[category]) < needed:
                    if backtrack(category):
                        return True
                
                assigned.remove(problem)
                assignments[category].pop()
        
        return False

    if backtrack(1):
        return 1, assignments
    return 0, None

def main():
    for line in sys.stdin:
        try:
            nk, np = map(int, line.split())
            if nk == 0 and np == 0:
                break
            
            problems_needed = list(map(int, input().split()))
            if len(problems_needed) != nk:
                raise ValueError("Number of categories doesn't match the input")
            
            problem_pool = defaultdict(list)
            
            for i in range(1, np + 1):
                categories = list(map(int, input().split()))
                for category in categories:
                    if 1 <= category <= nk:
                        problem_pool[category].append(i)
            
            success, assignments = can_assign_problems(nk, problems_needed, problem_pool)
            
            print(success)
            if success:
                for category in range(1, nk + 1):
                    print(' '.join(map(str, sorted(assignments[category]))))
        except Exception as e:
            print(f"Error processing input: {e}")
            print(0)  # Print failure in case of any error

if __name__ == "__main__":
    main()
