from datetime import datetime, timedelta
from typing import Dict, List, Tuple
import csv


def generate_team_ids(categories: Dict[int, int]) -> Dict[int, List[int]]:
    teams_by_category: Dict[int, List[int]] = {}
    for category, team_count in categories.items():
        base = category * 100
        teams_by_category[category] = [base + i for i in range(team_count)]
    return teams_by_category


def round_robin_pairings(team_ids: List[int], rounds: int) -> List[List[Tuple[int, int]]]:
    if len(team_ids) % 2 != 0:
        raise ValueError("Round-robin requires an even number of teams.")

    teams = list(team_ids)
    n = len(teams)
    half = n // 2
    max_rounds = n - 1
    used_rounds = min(rounds, max_rounds)
    schedule = []

    for _ in range(used_rounds):
        pairings = []
        for i in range(half):
            pairings.append((teams[i], teams[n - 1 - i]))
        schedule.append(pairings)
        teams = [teams[0]] + [teams[-1]] + teams[1:-1]

    return schedule


def generate_gameplan(
    categories: Dict[int, int],
    total_rounds: int = 5,
    start_time_str: str = "08:00",
    game_duration_minutes: int = 10,
):
    teams_by_category = generate_team_ids(categories)

    rr_by_category = {
        category: round_robin_pairings(team_ids, total_rounds)
        for category, team_ids in teams_by_category.items()
    }

    # First build all games grouped by round
    rounds_games: List[List[Dict]] = [[] for _ in range(total_rounds)]
    game_id = 1

    for round_idx in range(total_rounds):
        for category in sorted(categories.keys()):
            for home, guest in rr_by_category[category][round_idx]:
                rounds_games[round_idx].append(
                    {
                        "round": round_idx + 1,
                        "game_id": game_id,
                        "home": home,
                        "guest": guest,
                        "category": category,
                    }
                )
                game_id += 1

    start_time = datetime.strptime(start_time_str, "%H:%M")
    current_time = start_time
    final_plan = []

    # Assign hall & time (parallel halls per timeslot)
    for games in rounds_games:
        i = 0
        while i < len(games):
            # Hall A
            g1 = games[i]
            g1["hall"] = "A"
            g1["time"] = current_time.strftime("%H:%M")
            final_plan.append(g1)

            # Hall B
            if i + 1 < len(games):
                g2 = games[i + 1]
                g2["hall"] = "B"
                g2["time"] = current_time.strftime("%H:%M")
                final_plan.append(g2)

            current_time += timedelta(minutes=game_duration_minutes)
            i += 2

    return final_plan


def print_gameplan_by_hall(gameplan):
    halls = {"A": [], "B": []}
    for g in gameplan:
        halls[g["hall"]].append(g)

    for hall in ("A", "B"):
        print("\n======================")
        print(f"        HALL {hall}")
        print("======================")
        print(f"{'Rnd':>3} {'GID':>4} {'Time':>5} {'Home':>5} {'Guest':>5} {'Cat':>3}")

        for g in sorted(halls[hall], key=lambda x: (x["round"], x["time"], x["game_id"])):
            print(
                f"{g['round']:3d} {g['game_id']:4d} {g['time']:>5} "
                f"{g['home']:5d} {g['guest']:5d} {g['category']:3d}"
            )


# 🔥 NEW: CSV EXPORT
def export_gameplan_csv(gameplan, filename):
    """
    Exports the gameplan to a CSV file.
    Columns: round, game_id, time, hall, home, guest, category
    """
    fieldnames = ["round", "game_id", "time", "hall", "home", "guest", "category"]

    with open(filename, "w", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(gameplan)

    print(f"CSV exported to: {filename}")


# Example usage
if __name__ == "__main__":
    categories = {1: 6, 2: 8, 3: 8, 4: 6}

    gameplan = generate_gameplan(categories)

    print_gameplan_by_hall(gameplan)

    # export to CSV
    export_gameplan_csv(gameplan, "gameplan.csv")
